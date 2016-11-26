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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.PriorityBlockingQueue;

public class ConcreteInstanceType<T> implements InstanceType<T> {

  public static final int LENGTH_OF_SETTER_PREFIX = 3;
  private final TypeToken<T> typeToken;

  public ConcreteInstanceType(final TypeToken<T> typeToken) {
    this.typeToken = typeToken;
  }

  private static <TWrappedType> InstanceType<TWrappedType> from(final TypeToken<TWrappedType> typeToken) {
    return new ConcreteInstanceType<>(typeToken);
  }

  @Override
  public InstanceType<?> getArrayElementType() {
    return ConcreteInstanceType.from(typeToken.getComponentType());
  }

  @Override
  public <TAssignable> boolean isAssignableFrom(final Class<TAssignable> clazz) {
    return typeToken.isSubtypeOf(clazz);
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
  public Invokable<T, T> constructor(final Constructor<?> constructor) {
    return typeToken.constructor(constructor);
  }

  @Override
  public Object createArray(final Object[] objects) {
    final T[] array = CollectionFactory.createArray(typeToken, objects.length);
    for (int i = 0; i < objects.length; ++i) {
      Array.set(array, i, objects[i]);
    }
    return array;
  }

  @Override
  public boolean isAssignableTo(final Class<?> clazz) {
    return clazz.isAssignableFrom(typeToken.getRawType());
  }

  @Override
  public boolean isRawTypeAssignableFrom(final Class<?> clazz) {
    return typeToken.getRawType().isAssignableFrom(clazz);
  }

  @Override
  public <TOther> boolean isSameAsThatOf(final TOther injectedValue) {
    return typeToken.getRawType().equals(injectedValue.getClass());
  }

  @Override
  public boolean isCompatibleWith(final Class<?> clazz) {
    return Primitives.wrap(this.typeToken.getRawType()) == Primitives.wrap(clazz);
  }

  @Override
  public boolean isCompatibleWithAnyOf(final Class<?>... clazzes) {
    for (final Class<?> clazz : clazzes) {
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
  public Collection createCollection(final int repeatCount) {
    final Collection collection;
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
    if (typeToken == null) {
      result = prime * result + 0;
    } else {
      result = prime * result + typeToken.hashCode();
    }
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("rawtypes") final
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
    final ParameterizedType genericTypeDefinition = (ParameterizedType) (this.getType());
    final Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
    final TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
    return ConcreteInstanceType.from(nestedGenericTypeToken);
  }

  @Override
  public Call<T, T> findPublicConstructorWithLeastParameters() {
    final Constructor<?>[] constructors =
        getConstructorsSortedFromLongestToShortestParametersCount();

    int currentArgumentCount = Integer.MAX_VALUE;
    Optional<Invokable<T, T>> currentConstructor = Optional.absent();

    for (final Constructor<?> constructor : constructors) {
      final Invokable<T, T> invokable = typeToken.constructor(constructor);
      final int invokableParametersCount = invokable.getParameters().size();
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
    final Constructor<?>[] constructors = this.getRawType().getConstructors();

    Arrays.sort(constructors, (arg0, arg1) -> {
      final Invokable<T, T> invokable1 = typeToken.constructor(arg0);
      final Invokable<T, T> invokable2 = typeToken.constructor(arg1);
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
    final ArrayList<Call<T, Object>> setters = new ArrayList<>();

    final Method[] methods = typeToken.getRawType().getMethods();

    for (final Method mtd : methods) {
      final Invokable<T, Object> invokable = typeToken.method(mtd);
      if (isSetter(invokable)) {
        setters.add(MethodCall.to(invokable));
      }
    }
    return setters;
  }

  private boolean isSetter(final Invokable<T, Object> invokable) {
    return isNamedLikeASetter(invokable)
        && hasSingleArgument(invokable)
        && hasNoReturnValue(invokable);
  }

  private boolean hasNoReturnValue(final Invokable<T, Object> invokable) {
    return invokable.getReturnType().getRawType() == void.class;
  }

  private boolean hasSingleArgument(final Invokable<T, Object> invokable) {
    return invokable.getParameters().size() == 1;
  }

  private boolean isNamedLikeASetter(final Invokable<T, Object> invokable) {
    final String name = invokable.getName();
    return
        name.length() > LENGTH_OF_SETTER_PREFIX
            && name.startsWith("set")
            && Character.isUpperCase(name.charAt(LENGTH_OF_SETTER_PREFIX));
  }

  @Override
  public ArrayList<InstanceField<T>> getAllPublicFieldsOf(final T instance) {
    final ArrayList<InstanceField<T>> fields = new ArrayList<>();
    final Field[] fieldsArray = typeToken.getRawType().getDeclaredFields();
    for (final Field field : fieldsArray) {
      if (Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
        fields.add(new InstanceField<>(field, this, instance));
      }
    }
    return fields;
  }

  @Override
  public TypeToken<?> resolveActualTypeOf(final Field field) {
    return this.typeToken.resolveType(field.getGenericType());
  }

  @Override
  public TypeToken<?> resolveActualTypeOf(final Method method) {
    return this.typeToken.resolveType(method.getGenericReturnType());
  }

  @Override
  public String toString() {
    return typeToken.toString();
  }

}
