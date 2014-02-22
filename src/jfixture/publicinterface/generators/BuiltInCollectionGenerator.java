package jfixture.publicinterface.generators;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.PriorityBlockingQueue;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class BuiltInCollectionGenerator implements ComplexObjectGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> clazz) {
		return Collection.class.isAssignableFrom(clazz.getRawType());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T next(TypeToken<T> clazz, Fixture fixture) {
		Collection collection;
		if(clazz.getRawType().isAssignableFrom(HashSet.class)) {
			collection = new HashSet();
		} else if(clazz.getRawType().isAssignableFrom(TreeSet.class)) {
			collection = new TreeSet();
		} else if (clazz.getRawType().isAssignableFrom(PriorityQueue.class)) {
			collection = new PriorityQueue();
		} else if (clazz.getRawType().isAssignableFrom(PriorityBlockingQueue.class)) {
			collection = new PriorityBlockingQueue();	
		} else if (clazz.getRawType().isAssignableFrom(CopyOnWriteArraySet.class)) {
			collection = new CopyOnWriteArraySet();
		} else if (clazz.getRawType().isAssignableFrom(CopyOnWriteArrayList.class)) {
			collection = new CopyOnWriteArrayList();
		} else if(clazz.getRawType().isAssignableFrom(ConcurrentSkipListSet.class)) {
			collection = new ConcurrentSkipListSet();
		} else if(clazz.getRawType().isAssignableFrom(ConcurrentLinkedQueue.class)) {
			collection = new ConcurrentLinkedQueue();
		} else if(clazz.getRawType().isAssignableFrom(LinkedList.class)) {
			collection = new LinkedList();
		} else if(clazz.getRawType().isAssignableFrom(LinkedHashSet.class)) {
			collection = new LinkedHashSet();
		} else if(clazz.getRawType().isAssignableFrom(ArrayBlockingQueue.class)) {
			collection = new ArrayBlockingQueue(3);
		} else if(clazz.getRawType().isAssignableFrom(ArrayDeque.class)) {
			collection = new ArrayDeque();
		} else if(clazz.getRawType().isAssignableFrom(Stack.class)) {
			collection = new Stack();
		} else {
			collection = new ArrayList();
		}
		
		TypeToken<?> nestedGenericTypeToken = getNestedGenericTypeTokenFrom(clazz);
		Object instance1 = fixture.create(nestedGenericTypeToken);
		Object instance2 = fixture.create(nestedGenericTypeToken);
		Object instance3 = fixture.create(nestedGenericTypeToken);
		
		collection.add(instance1);
		collection.add(instance2);
		collection.add(instance3);
		
		return (T) collection;
	}

	private <T> TypeToken<?> getNestedGenericTypeTokenFrom(TypeToken<T> clazz) {
		ParameterizedType genericTypeDefinition = (ParameterizedType)(clazz.getType());
		Type nestedGenericType = genericTypeDefinition.getActualTypeArguments()[0];
		
		TypeToken<?> nestedGenericTypeToken = TypeToken.of(nestedGenericType);
		return nestedGenericTypeToken;
	}

}
