package autofixture.publicinterface.generators.implementationdetails;

import autofixture.implementationdetails.InstanceField;
import autofixture.publicinterface.CollectionFactory;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;
import autofixture.publicinterface.generators.Call;
import com.google.common.base.Optional;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

public class ConcreteInstanceType<T> implements InstanceType<T> {

  private TypeToken<T> typeToken;

  public ConcreteInstanceType(TypeToken<T> typeToken) {
    this.typeToken = typeToken;
  }

  private static <TWrappedType> InstanceType<TWrappedType> from(TypeToken<TWrappedType> typeToken) {
    return new ConcreteInstanceType<>(typeToken);
  }

  @Override
  public InstanceType<?> getArrayElementType() {
    return ConcreteInstanceType.from(typeToken.getComponentType());
  }

  @Override
  public <TAssignable> boolean isAssignableFrom(Class<TAssignable> clazz) {
    return typeToken.isAssignableFrom(clazz);
  }

  @Override
  public Class<? super T> getRawType() {
    return typeToken.getRawType();
  }

  @Override
  public TypeToken<? super T> getToken() {
    return typeToken;
  }

  @Override
  public Type getType() {
    return typeToken.getType();
  }

  @Override
  public boolean isArray() {
    return typeToken.isArray();
  }

  @Override
  public Invokable<T, T> constructor(Constructor<?> constructor) {
    return typeToken.constructor(constructor);
  }

  @Override
  public Object createArray(Object[] objects) {
    Object array = Array.newInstance(typeToken.getRawType(), objects.length);
    for (int i = 0; i < objects.length; ++i) {
      Array.set(array, i, objects[i]);
    }
    return array;
  }

  @Override
  public boolean isAssignableTo(Class<?> clazz) {
    return clazz.isAssignableFrom(typeToken.getRawType());
  }

  @Override
  public boolean isRawTypeAssignableFrom(Class<?> clazz) {
    return typeToken.getRawType().isAssignableFrom(clazz);
  }

  @Override
  public <TOther> boolean isSameAsThatOf(TOther injectedValue) {
    return typeToken.getRawType().equals(injectedValue.getClass());
  }

  @Override
  public boolean isCompatibleWith(Class<?> clazz) {
    return Primitives.wrap(this.typeToken.getRawType()) == Primitives.wrap(clazz);
  }

  @Override
  public boolean isCompatibleWithAnyOf(Class<?>... clazzes) {
    for (Class<?> clazz : clazzes) {
      if (this.isCompatibleWith(clazz)) {
        return true;
      }
    }
    return false;
  }

  public Type getWrapper() {
    return Primitives.wrap(this.getRawType());
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Collection createCollection(int repeatCount) {
    Collection collection;
    if (this.isRawTypeAssignableFrom(HashSet.class)) {
      collection = CollectionFactory.createEmptySet();
    } else if (this.isRawTypeAssignableFrom(TreeSet.class)) {
      collection = CollectionFactory.createTreeSet();
    } else if (this.isRawTypeAssignableFrom(PriorityQueue.class)) {
      collection = CollectionFactory.createEmptyPriorityQueue();
    } else if (this.isRawTypeAssignableFrom(PriorityBlockingQueue.class)) {
      collection = CollectionFactory.createEmptyPriorityBlockingQueue();
    } else if (this.isRawTypeAssignableFrom(CopyOnWriteArraySet.class)) {
      collection = CollectionFactory.createEmptyCopyOnWriteArraySet();
    } else if (this.isRawTypeAssignableFrom(CopyOnWriteArrayList.class)) {
      collection = CollectionFactory.createEmptyCopyOnWriteArrayList();
    } else if (this.isRawTypeAssignableFrom(ConcurrentSkipListSet.class)) {
      collection = CollectionFactory.createEmptyConcurrentSkipListSet();
    } else if (this.isRawTypeAssignableFrom(ConcurrentLinkedQueue.class)) {
      collection = CollectionFactory.createEmptyConcurrentLinkedQueue();
    } else if (this.isRawTypeAssignableFrom(LinkedList.class)) {
      collection = CollectionFactory.createEmptyLinkedList();
    } else if (this.isRawTypeAssignableFrom(LinkedHashSet.class)) {
      collection = CollectionFactory.createEmptyLinkedHashSet();
    } else if (this.isRawTypeAssignableFrom(ArrayBlockingQueue.class)) {
      collection = CollectionFactory.createEmptyArrayBlockingQueue(repeatCount);
    } else if (this.isRawTypeAssignableFrom(ArrayDeque.class)) {
      collection = CollectionFactory.createEmptyArrayDeque();
    } else if (this.isRawTypeAssignableFrom(Stack.class)) {
      collection = CollectionFactory.createEmptyStack();
    } else {
      collection = CollectionFactory.createEmptyArrayList();
    }
    return collection;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
      + ((typeToken == null) ? 0 : typeToken.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("rawtypes")
    ConcreteInstanceType other = (ConcreteInstanceType) obj;
    if (typeToken == null) {
      if (other.typeToken != null)
        return false;
    } else if (!typeToken.equals(other.typeToken))
      return false;
    return true;
  }

  @Override
  public InstanceType<?> getNestedGenericType() {
    ParameterizedType genericTypeDefinition = (ParameterizedType) (this.getType());
    Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
    TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
    return ConcreteInstanceType.from(nestedGenericTypeToken);
  }

  @Override
  public Call<T, T> findPublicConstructorWithLeastParameters() {
    Constructor<?>[] constructors =
      getConstructorsSortedFromLongestToShortestParametersCount();

    int currentArgumentCount = Integer.MAX_VALUE;
    Optional<Invokable<T, T>> currentConstructor = Optional.absent();

    for (Constructor<?> constructor : constructors) {
      Invokable<T, T> invokable = typeToken.constructor(constructor);
      int invokableParametersCount = invokable.getParameters().size();
      if (invokable.isPublic() && invokableParametersCount < currentArgumentCount) {

        if (currentConstructor.isPresent() && invokableParametersCount == 0) {
          continue;
        }

        currentArgumentCount = invokableParametersCount;
        currentConstructor = Optional.of(invokable);
      }
    }

    if (!currentConstructor.isPresent()) {
      throw new ObjectCreationException(this, "Could not find any public constructor");
    }
    return MethodCall.to(currentConstructor.get());
  }

  private Constructor<?>[] getConstructorsSortedFromLongestToShortestParametersCount() {
    Constructor<?>[] constructors = this.getRawType().getConstructors();

    Arrays.sort(constructors, (arg0, arg1) -> {
      Invokable<T, T> invokable1 = typeToken.constructor(arg0);
      Invokable<T, T> invokable2 = typeToken.constructor(arg1);
      return Integer.compare(
        invokable2.getParameters().size(),
        invokable1.getParameters().size());
    });
    return constructors;
  }

  @Override
  public boolean isEnum() {
    return typeToken.getRawType().isEnum();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T[] getEnumConstants() {
    return (T[]) (typeToken.getRawType().getEnumConstants());
  }

  @Override
  public boolean isInterface() {
    return typeToken.getRawType().isInterface();
  }

  @Override
  public ArrayList<Call<T, Object>> getAllSetters() {
    ArrayList<Call<T, Object>> setters = new ArrayList<>();

    Method[] methods = typeToken.getRawType().getMethods();

    for (Method mtd : methods) {
      Invokable<T, Object> invokable = typeToken.method(mtd);
      if (isSetter(invokable)) {
        setters.add(MethodCall.to(invokable));
      }
    }
    return setters;
  }

  private boolean isSetter(Invokable<T, Object> invokable) {
    return isNamedLikeASetter(invokable)
      && hasSingleArgument(invokable)
      && hasNoReturnValue(invokable);
  }

  private boolean hasNoReturnValue(Invokable<T, Object> invokable) {
    return invokable.getReturnType().getRawType() == void.class;
  }

  private boolean hasSingleArgument(Invokable<T, Object> invokable) {
    return invokable.getParameters().size() == 1;
  }

  private boolean isNamedLikeASetter(Invokable<T, Object> invokable) {
    String name = invokable.getName();
    return
      name.length() > 3
        && name.startsWith("set")
        && Character.isUpperCase(name.charAt(3));
  }

  @Override
  public ArrayList<InstanceField<T>> getAllPublicFieldsOf(T instance) {
    ArrayList<InstanceField<T>> fields = new ArrayList<>();
    Field[] fieldsArray = typeToken.getRawType().getDeclaredFields();
    for (Field field : fieldsArray) {
      if (Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
        fields.add(new InstanceField<>(field, this, instance));
      }
    }
    return fields;
  }

  @Override
  public TypeToken<?> resolveActualTypeOf(Field field) {
    return this.typeToken.resolveType(field.getGenericType());
  }

  @Override
  public TypeToken<?> resolveActualTypeOf(Method method) {
    return this.typeToken.resolveType(method.getGenericReturnType());
  }

  @Override
  public String toString() {
    return typeToken.toString();
  }

}
