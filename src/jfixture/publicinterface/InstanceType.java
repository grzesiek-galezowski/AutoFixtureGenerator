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

public class InstanceType<T> {

	private TypeToken<T> typeToken;

	public InstanceType(TypeToken<T> typeToken) {
		this.typeToken = typeToken;
	}

	//TODO reduce even further
	public InstanceType<?> getArrayElementType() {
		return new InstanceType(typeToken.getComponentType());
	}

	public <TAssignable> boolean isAssignableFrom(Class<TAssignable> clazz) {
		return typeToken.isAssignableFrom(clazz);
	}

	public Class<? super T> getRawType() {
		return typeToken.getRawType();
	}

	public Type getType() {
		return typeToken.getType();
	}

	public boolean isArray() {
		return typeToken.isArray();
	}

	public Invokable<T, T> constructor(Constructor<?> constructor) {
		return typeToken.constructor(constructor);
	}

	public Object createArray(Object[] objects) {
		
		Object array = Array.newInstance(typeToken.getRawType(), objects.length);
		for(int i = 0 ; i < objects.length ; ++i) {
			Array.set(array, i, objects[i]);
		}
		return array;
	}

	public boolean IsAssignableTo(Class<?> clazz) {
		return clazz.isAssignableFrom(typeToken.getRawType());
	}

	public boolean isRawTypeAssignableFrom(Class<?> clazz) {
		return typeToken.getRawType().isAssignableFrom(clazz);
	}
	
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
	
	public InstanceType<?> getNestedGenericType() {
		ParameterizedType genericTypeDefinition = (ParameterizedType)(this.getType());
		Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
		
		TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
		return new InstanceType(nestedGenericTypeToken);
	}

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

	public boolean isEnum() {
		return typeToken.getRawType().isEnum();
	}

	public Object[] getEnumConstants() {
		return typeToken.getRawType().getEnumConstants();
	}
	
	
	//TODO reimplement toString();

}
