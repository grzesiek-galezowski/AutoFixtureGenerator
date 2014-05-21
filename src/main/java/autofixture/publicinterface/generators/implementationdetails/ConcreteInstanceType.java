package autofixture.publicinterface.generators.implementationdetails;

import autofixture.implementationdetails.InstanceField;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;
import autofixture.publicinterface.generators.Call;
import com.google.common.base.Optional;
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
		for(int i = 0 ; i < objects.length ; ++i) {
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public Collection createCollection() {
		Collection collection;
		if(this.isRawTypeAssignableFrom(HashSet.class)) {
			collection = new HashSet();
		} else if(this.isRawTypeAssignableFrom(TreeSet.class)) {
			collection = new TreeSet();
		} else if (this.isRawTypeAssignableFrom(PriorityQueue.class)) {
			collection = new PriorityQueue();
		} else if (this.isRawTypeAssignableFrom(PriorityBlockingQueue.class)) {
			collection = new PriorityBlockingQueue();	
		} else if (this.isRawTypeAssignableFrom(CopyOnWriteArraySet.class)) {
			collection = new CopyOnWriteArraySet();
		} else if (this.isRawTypeAssignableFrom(CopyOnWriteArrayList.class)) {
			collection = new CopyOnWriteArrayList();
		} else if(this.isRawTypeAssignableFrom(ConcurrentSkipListSet.class)) {
			collection = new ConcurrentSkipListSet();
		} else if(this.isRawTypeAssignableFrom(ConcurrentLinkedQueue.class)) {
			collection = new ConcurrentLinkedQueue();
		} else if(this.isRawTypeAssignableFrom(LinkedList.class)) {
			collection = new LinkedList();
		} else if(this.isRawTypeAssignableFrom(LinkedHashSet.class)) {
			collection = new LinkedHashSet();
		} else if(this.isRawTypeAssignableFrom(ArrayBlockingQueue.class)) {
			collection = new ArrayBlockingQueue(3);
		} else if(this.isRawTypeAssignableFrom(ArrayDeque.class)) {
			collection = new ArrayDeque();
		} else if(this.isRawTypeAssignableFrom(Stack.class)) {
			collection = new Stack();
		} else {
			collection = new ArrayList();
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
		ParameterizedType genericTypeDefinition = (ParameterizedType)(this.getType());
		Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
		
		TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
		return ConcreteInstanceType.from(nestedGenericTypeToken);
	}

	private static <TWrappedType> InstanceType<TWrappedType> from(TypeToken<TWrappedType> typeToken) {
		return new ConcreteInstanceType<>(typeToken);
	}

	@Override
	public Call<T, T> findPublicConstructorWithLeastParameters() {
		Constructor<?>[] constructors = 
				getConstructorsSortedFromLongestToShortestParametersCount();
		
		int currentArgumentCount = Integer.MAX_VALUE;
		Optional<Invokable<T, T>> currentConstructor = Optional.absent();
		
		for(Constructor<?> constructor : constructors) {
			Invokable<T, T> invokable = typeToken.constructor(constructor);
			int invokableParametersCount = invokable.getParameters().size();
			if(invokable.isPublic() && invokableParametersCount < currentArgumentCount) {
				
				if(currentConstructor.isPresent() && invokableParametersCount == 0) {
					continue;
				}
				
				currentArgumentCount = invokableParametersCount;
				currentConstructor = Optional.of(invokable);
			}
		}

		if(!currentConstructor.isPresent()) {
			throw new ObjectCreationException(this, "Could not find any public constructor");
		}
		return MethodCall.to(currentConstructor.get());
	}

	private Constructor<?>[] getConstructorsSortedFromLongestToShortestParametersCount() {
		Constructor<?>[] constructors = this.getRawType().getConstructors();

		Arrays.sort(constructors, new Comparator<Constructor<?>>() {

			@Override
			public int compare(Constructor<?> arg0, Constructor<?> arg1) {
				Invokable<T, T> invokable1 = typeToken.constructor(arg0);
				Invokable<T, T> invokable2 = typeToken.constructor(arg1);
				return Integer.compare(
						invokable2.getParameters().size(),
						invokable1.getParameters().size());
			}
			
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
		return (T[])(typeToken.getRawType().getEnumConstants());
	}

	@Override
	public boolean isInterface() {
		return typeToken.getRawType().isInterface();
	}

	@Override
	public ArrayList<Call<T, Object>> getAllSetters() {
		ArrayList<Call<T, Object>> setters = new ArrayList<>();
		
		Method[] methods = typeToken.getRawType().getMethods();
		
		for(Method mtd : methods) {
			Invokable<T, Object> invokable = typeToken.method(mtd);
			if(isSetter(invokable)) {
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
				&&  name.startsWith("set") 
				&& Character.isUpperCase(name.charAt(3));
	}

	@Override
	public ArrayList<InstanceField<T>> getAllPublicFieldsOf(T instance) {
		ArrayList<InstanceField<T>> fields = new ArrayList<>();
		Field[] fieldsArray = typeToken.getRawType().getDeclaredFields();
		for(Field field : fieldsArray) {
			if(Modifier.isPublic(field.getModifiers()) && ! Modifier.isStatic(field.getModifiers())) {
				fields.add(new InstanceField<>(field, this, instance));
			}
		}
		return fields;
	}

	@Override
	public TypeToken<?> ResolveActualTypeOf(Field field) {
		return this.typeToken.resolveType(field.getGenericType());
	}

    @Override
    public String toString() {
        return typeToken.toString();
    }

}
