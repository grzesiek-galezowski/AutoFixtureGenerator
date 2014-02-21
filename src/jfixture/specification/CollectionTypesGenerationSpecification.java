package jfixture.specification;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.PriorityBlockingQueue;

import com.google.common.reflect.*;

import jfixture.publicinterface.Fixture;
import jfixture.specification.matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class CollectionTypesGenerationSpecification {

	/*
	 TODO
	  TODO generic versions
	  TODO non-generic versions
	  TODO collections of collections!
	 */
	
	Fixture fixture = new Fixture();
	
	@Test
	public void ShouldGenerateArraysWithThreeUniqueElements() {
		String[] array = fixture.create(TypeToken.of(String[].class));
		
		assertThat(array, hasLength(3));
		assertThat(array, hasUniqueItems());
	}
	
	@DataPoint
	public static TypeToken<Collection<String>> collectionClass = new TypeToken<Collection<String>>() {};
	@DataPoint
	public static TypeToken<AbstractCollection<String>> abstractCollectionClass = new TypeToken<AbstractCollection<String>>() {};
	@DataPoint
	public static TypeToken<ArrayList<String>> arrayListClass = new TypeToken<ArrayList<String>>() {};
	@DataPoint
	public static TypeToken<Set<String>> setClass = new TypeToken<Set<String>>() {};
	@DataPoint
	public static TypeToken<SortedSet<String>> sortedSetClass = new TypeToken<SortedSet<String>>() {};
	@DataPoint
	public static TypeToken<TreeSet<String>> treeSetClass = new TypeToken<TreeSet<String>>() {};
	@DataPoint
	public static TypeToken<HashSet<String>> hashSetClass = new TypeToken<HashSet<String>>() {};
	@DataPoint
	public static TypeToken<PriorityQueue<String>> priorityQueueClass = new TypeToken<PriorityQueue<String>>() {};
	@DataPoint
	public static TypeToken<Queue<String>> queueClass = new TypeToken<Queue<String>>() {};
	@DataPoint
	public static TypeToken<NavigableSet<String>> navigableSetClass = new TypeToken<NavigableSet<String>>() {};
	@DataPoint
	public static TypeToken<AbstractSet<String>> abstractSetClass = new TypeToken<AbstractSet<String>>() {};
	@DataPoint
	public static TypeToken<AbstractQueue<String>> abstractQueueClass = new TypeToken<AbstractQueue<String>>() {};
	@DataPoint
	public static TypeToken<AbstractList<String>> abstractListClass = new TypeToken<AbstractList<String>>() {};
	@DataPoint
	public static TypeToken<CopyOnWriteArraySet<String>> copyOnWriteArrayClass = new TypeToken<CopyOnWriteArraySet<String>>() {};
	@DataPoint
	public static TypeToken<ConcurrentSkipListSet<String>> concurrentSkipListSetClass = new TypeToken<ConcurrentSkipListSet<String>>() {};
	@DataPoint
	public static TypeToken<AbstractSequentialList<String>> abstractSequentialListClass = new TypeToken<AbstractSequentialList<String>>() {};
	@DataPoint
	public static TypeToken<LinkedList<String>> linkedListClass = new TypeToken<LinkedList<String>>() {};
	@DataPoint
	public static TypeToken<ArrayBlockingQueue<String>> arrayBlockingQueueClass = new TypeToken<ArrayBlockingQueue<String>>() {};
	@DataPoint
	public static TypeToken<ArrayDeque<String>> arrayDequeClass = new TypeToken<ArrayDeque<String>>() {};
	@DataPoint
	public static TypeToken<ConcurrentLinkedQueue<String>> concurrentLinkedQueueClass = new TypeToken<ConcurrentLinkedQueue<String>>() {};
	@DataPoint
	public static TypeToken<CopyOnWriteArrayList<String>> copyOnWriteArrayListClass = new TypeToken<CopyOnWriteArrayList<String>>() {};
	@DataPoint
	public static TypeToken<LinkedHashSet<String>> linkedHashSetClass = new TypeToken<LinkedHashSet<String>>() {};
	@DataPoint
	public static TypeToken<PriorityBlockingQueue<String>> priorityBlockingQueueClass = new TypeToken<PriorityBlockingQueue<String>>() {};
	@DataPoint
	public static TypeToken<Stack<String>> stackClass = new TypeToken<Stack<String>>() {};

	
	@Theory
	public <T extends Collection<String>> void ShouldGenerateCollectionsWithThreeUniqueElements(
			TypeToken<T> collectionClass) {
		System.out.println(collectionClass);
		
		T collection = (T)fixture.create(collectionClass);
		
		assertTrue("Cannot assign " + collection.getClass()+ " to " + collectionClass, 
				collectionClass.getRawType().isAssignableFrom(collection.getClass()));
		
		assertThat(collection.toArray(TypeOfString()), hasLength(3));
		assertThat(collection.toArray(TypeOfString()), hasUniqueItems());
		System.out.println("OK");
	}


	private String[] TypeOfString() {
		return new String[]{};
	}
	

	private Matcher<? super String[]> hasUniqueItems() {
		return new HasArrayUniqueItemsMatcher();
	}

	private Matcher<? super String[]> hasLength(int i) {
		return new HasArrayLengthMatcher(i);
	}

	
	
}
