package jfixture.publicinterface;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
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

import com.google.common.base.Optional;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

public class ConcreteInstanceType<T> implements InstanceType<T> {

	private TypeToken<T> typeToken;

	public ConcreteInstanceType(TypeToken<T> typeToken) {
		this.typeToken = typeToken;
	}

	//TODO reduce even further
	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#getArrayElementType()
	 */
	@Override
	public InstanceType<?> getArrayElementType() {
		return new ConcreteInstanceType(typeToken.getComponentType());
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#isAssignableFrom(java.lang.Class)
	 */
	@Override
	public <TAssignable> boolean isAssignableFrom(Class<TAssignable> clazz) {
		return typeToken.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#getRawType()
	 */
	@Override
	public Class<? super T> getRawType() {
		return typeToken.getRawType();
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#getToken()
	 */
	@Override
	public TypeToken<? super T> getToken() {
		return typeToken;
	}
	
	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#getType()
	 */
	@Override
	public Type getType() {
		return typeToken.getType();
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#isArray()
	 */
	@Override
	public boolean isArray() {
		return typeToken.isArray();
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#constructor(java.lang.reflect.Constructor)
	 */
	@Override
	public Invokable<T, T> constructor(Constructor<?> constructor) {
		return typeToken.constructor(constructor);
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#createArray(java.lang.Object[])
	 */
	@Override
	public Object createArray(Object[] objects) {
		
		Object array = Array.newInstance(typeToken.getRawType(), objects.length);
		for(int i = 0 ; i < objects.length ; ++i) {
			Array.set(array, i, objects[i]);
		}
		return array;
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#IsAssignableTo(java.lang.Class)
	 */
	@Override
	public boolean IsAssignableTo(Class<?> clazz) {
		return clazz.isAssignableFrom(typeToken.getRawType());
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#isRawTypeAssignableFrom(java.lang.Class)
	 */
	@Override
	public boolean isRawTypeAssignableFrom(Class<?> clazz) {
		return typeToken.getRawType().isAssignableFrom(clazz);
	}
	
	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#createCollection()
	 */
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
	
	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#getNestedGenericType()
	 */
	@Override
	public InstanceType<?> getNestedGenericType() {
		ParameterizedType genericTypeDefinition = (ParameterizedType)(this.getType());
		Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
		
		TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
		return new ConcreteInstanceType(nestedGenericTypeToken);
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#findPublicConstructorWithLeastArguments()
	 */
	@Override
	public Invokable<T, T> findPublicConstructorWithLeastArguments() {
		Constructor<?>[] constructors = this.getRawType().getConstructors();
		
		int currentArgumentCount = 10000;
		Optional<Invokable<T, T>> currentConstructor = Optional.absent(); 
		
		for(Constructor<?> constructor : constructors) {
			Invokable<T, T> invokable = typeToken.constructor(constructor);
			int invokableParametersCount = invokable.getParameters().size();
			if(invokable.isPublic() && invokableParametersCount < currentArgumentCount) {
				currentArgumentCount = invokableParametersCount;
				currentConstructor = Optional.of(invokable);
			}
		}

		if(!currentConstructor.isPresent()) {
			throw new ObjectCreationException(this, "Could not find any public constructor");
		}
		return currentConstructor.get();
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#isEnum()
	 */
	@Override
	public boolean isEnum() {
		return typeToken.getRawType().isEnum();
	}

	/* (non-Javadoc)
	 * @see jfixture.publicinterface.IInstanceType#getEnumConstants()
	 */
	@Override
	public Object[] getEnumConstants() {
		return typeToken.getRawType().getEnumConstants();
	}
	
	
	//TODO reimplement toString();

}
