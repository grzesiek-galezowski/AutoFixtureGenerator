package autofixture.generators.objects.implementationdetails;

import autofixture.exceptions.ObjectCreationException;
import autofixture.implementationdetails.CollectionFactory;
import autofixture.interfaces.Call;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceField;
import autofixture.interfaces.InstanceType;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import lombok.val;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

public class ConcreteInstanceType<T> implements InstanceType<T> {

  public static final int LENGTH_OF_SETTER_PREFIX = 3;
  private final TypeToken<T> typeToken;
  private final List<ConstructorVisibility<T>> constructorVisibilities;

  public ConcreteInstanceType(final TypeToken<T> typeToken) {
    this.typeToken = typeToken;
    constructorVisibilities = Arrays.asList(
        ConstructorVisibility.getPublic(),
        ConstructorVisibility.getPackagePrivate());
  }

  private static <TWrappedType> InstanceType<TWrappedType> from(final TypeToken<TWrappedType> typeToken) {
    return new ConcreteInstanceType<>(typeToken);
  }

  public static <TWrappedType> ConcreteInstanceType<TWrappedType> fromClass(Class<TWrappedType> type) {
      return new ConcreteInstanceType<>(TypeToken.of(type));
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
  public Object createEmptyArray() {
    final T[] array = CollectionFactory.createArray(typeToken, 0);
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

  @Override
  public boolean isFromPackage(final String packageName) {
    return this.typeToken.getRawType().getName().startsWith(packageName);
  }


  @Override
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
  public Map createEmptyMap() {
    if (this.isRawTypeAssignableFrom(HashMap.class)) {
      return CollectionFactory.createEmptyHashMap();
    }
    if (this.isRawTypeAssignableFrom(SortedMap.class)) {
      return CollectionFactory.createEmptySortedMap();
    }
    if (this.isRawTypeAssignableFrom(TreeMap.class)) {
      return CollectionFactory.createEmptyTreeMap();
    }
    if (this.isRawTypeAssignableFrom(NavigableMap.class)) {
      return CollectionFactory.createEmptyNavigableMap();
    }
    if (this.isRawTypeAssignableFrom(ConcurrentHashMap.class)) {
      return CollectionFactory.createEmptyConcurrentHashMap();
    }
    if (this.isRawTypeAssignableFrom(ConcurrentSkipListMap.class)) {
      return CollectionFactory.createEmptyConcurrentSkipListMap();
    }
    if (this.isRawTypeAssignableFrom(Hashtable.class)) {
      return CollectionFactory.createEmptyHashtable();
    }
    if (this.isRawTypeAssignableFrom(LinkedHashMap.class)) {
      return CollectionFactory.createEmptyLinkedHashMap();
    }
    if (this.isRawTypeAssignableFrom(WeakHashMap.class)) {
      return CollectionFactory.createEmptyWeakHashMap();
    }
    if (this.isRawTypeAssignableFrom(IdentityHashMap.class)) {
      return CollectionFactory.createEmptyIdentityHashMap();
    }
    if (this.isRawTypeAssignableFrom(EnumMap.class)) {
      return CollectionFactory.createEmptyEnumMap((Class<Enum>)getNestedGenericType1().getRawType());
    }

    return CollectionFactory.createEmptyHashMap();

    /*
  //todo handle later
  public static TypeToken<Attributes> attributesClass = new TypeToken<Attributes>() {
  public static TypeToken<AuthProvider> authProviderClass = new TypeToken<AuthProvider>() {
  public static TypeToken<EnumMap<DayOfWeek, String>> enumMapClass = new TypeToken<EnumMap<DayOfWeek, String>>() {
  public static TypeToken<PrinterStateReasons> printerStateReasonsClass = new TypeToken<PrinterStateReasons>() {
  public static TypeToken<Properties> propertiesClass = new TypeToken<Properties>() {
  public static TypeToken<Provider> providerClass = new TypeToken<Provider>() {
  public static TypeToken<RenderingHints> renderingHintsClass = new TypeToken<RenderingHints>() {
  public static TypeToken<SimpleBindings> simpleBindingsClass = new TypeToken<SimpleBindings>() {
  public static TypeToken<TabularDataSupport> tabularDataSupportClass = new TypeToken<TabularDataSupport>() {
  public static TypeToken<UIDefaults> uiDefaultsClass = new TypeToken<UIDefaults>() {
  };
*/
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
  public InstanceType<?> getNestedGenericType1() {
    return getNestedGenericType(0);
  }

  @Override
  public InstanceType<?> getNestedGenericType2() {
    return getNestedGenericType(1);
  }

  private InstanceType<?> getNestedGenericType(int i) {
    final ParameterizedType genericTypeDefinition = (ParameterizedType) (this.getType());
    final Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[i];
    final TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
    return ConcreteInstanceType.from(nestedGenericTypeToken);
  }

  @Override
  public Call<T, T> findSuitableConstructorWithLeastParameters() {
    final List<InstanceCreation<T>> creationMethods =
        InstanceCreation.getPrioritizedCreationMethods(typeToken);

    for (val visibility : constructorVisibilities) {
      val suitableConstructor = InstanceCreation.findBestOf(creationMethods, visibility);

      if (suitableConstructor.isPresent()) {
        return MethodCall.to(suitableConstructor.get().getRawValue());
      }
    }

    throw new ObjectCreationException(this, "Could not find any suitable constructor");

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
  public boolean isAbstract() {
    return Modifier.isAbstract(typeToken.getRawType().getModifiers());
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
  public ArrayList<InstanceField<T>> getAllPublicSettableFieldsOf(final T instance) {
    final ArrayList<InstanceField<T>> fields = new ArrayList<>();
    final Field[] fieldsArray = typeToken.getRawType().getDeclaredFields();
    for (final Field field : fieldsArray) {
      if (Modifier.isPublic(field.getModifiers()) &&
          !Modifier.isStatic(field.getModifiers()) &&
          !Modifier.isFinal(field.getModifiers())) {
        fields.add(new ReflectionInstanceField<>(field, this, instance));
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

  @Override
  public Map turnIntoMapInstance(FixtureContract fixture) {
    Map map = new HashMap<>();
    for(int i = 0 ; i < fixture.getRepeatCount() ; ++i) {
      map.put(fixture.create(getNestedGenericType1()),
          fixture.create(getNestedGenericType2()));
    }
    return map;
  }

  @Override
  public Collection<?> turnIntoCollection(FixtureContract fixture) {
    return fixture.createMany(getNestedGenericType1());
  }
}

