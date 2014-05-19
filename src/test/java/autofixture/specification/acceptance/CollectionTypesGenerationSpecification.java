package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.specification.acceptance.matchers.HasArrayLengthMatcher;
import autofixture.specification.acceptance.matchers.HasArrayUniqueItemsMatcher;
import com.google.common.reflect.TypeToken;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class CollectionTypesGenerationSpecification {

	Fixture fixture = new Fixture();
	
	@Test
	public void shouldGenerateArraysWithThreeUniqueElements() {
		String[] array = fixture.create(TypeToken.of(String[].class));
		
		assertThat(array, hasLength(3));
		assertThat(array, hasUniqueItems());
	}
	
	@Theory
	public <T extends Collection<String>> void shouldGenerateCollectionsWithThreeUniqueElements(
			TypeToken<T> collectionClass) {
		T collection = fixture.create(collectionClass);
		
		assertTrue("Cannot assign " + collection.getClass()+ " to " + collectionClass, 
				collectionClass.getRawType().isAssignableFrom(collection.getClass()));
		
		assertThat(collection.toArray(TypeOfString()), hasLength(3));
		assertThat(collection.toArray(TypeOfString()), hasUniqueItems());
		System.out.println("OK");
	}

	
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<Collection<String>> collectionClass = new TypeToken<Collection<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<AbstractCollection<String>> abstractCollectionClass = new TypeToken<AbstractCollection<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<ArrayList<String>> arrayListClass = new TypeToken<ArrayList<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<Set<String>> setClass = new TypeToken<Set<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<SortedSet<String>> sortedSetClass = new TypeToken<SortedSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<TreeSet<String>> treeSetClass = new TypeToken<TreeSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<HashSet<String>> hashSetClass = new TypeToken<HashSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<PriorityQueue<String>> priorityQueueClass = new TypeToken<PriorityQueue<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<Queue<String>> queueClass = new TypeToken<Queue<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<NavigableSet<String>> navigableSetClass = new TypeToken<NavigableSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<AbstractSet<String>> abstractSetClass = new TypeToken<AbstractSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<AbstractQueue<String>> abstractQueueClass = new TypeToken<AbstractQueue<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<AbstractList<String>> abstractListClass = new TypeToken<AbstractList<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<CopyOnWriteArraySet<String>> copyOnWriteArrayClass = new TypeToken<CopyOnWriteArraySet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<ConcurrentSkipListSet<String>> concurrentSkipListSetClass = new TypeToken<ConcurrentSkipListSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<AbstractSequentialList<String>> abstractSequentialListClass = new TypeToken<AbstractSequentialList<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<LinkedList<String>> linkedListClass = new TypeToken<LinkedList<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<ArrayBlockingQueue<String>> arrayBlockingQueueClass = new TypeToken<ArrayBlockingQueue<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<ArrayDeque<String>> arrayDequeClass = new TypeToken<ArrayDeque<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<ConcurrentLinkedQueue<String>> concurrentLinkedQueueClass = new TypeToken<ConcurrentLinkedQueue<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<CopyOnWriteArrayList<String>> copyOnWriteArrayListClass = new TypeToken<CopyOnWriteArrayList<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<LinkedHashSet<String>> linkedHashSetClass = new TypeToken<LinkedHashSet<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<PriorityBlockingQueue<String>> priorityBlockingQueueClass = new TypeToken<PriorityBlockingQueue<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<Stack<String>> stackClass = new TypeToken<Stack<String>>() {};
	@SuppressWarnings("serial")
	@DataPoint
	public static TypeToken<Iterable<String>> iterableClass = new TypeToken<Iterable<String>>() {};

	private String[] TypeOfString() {
		return new String[]{};
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Matcher<? super String[]> hasUniqueItems() {
		return new HasArrayUniqueItemsMatcher();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Matcher<? super String[]> hasLength(int i) {
		return new HasArrayLengthMatcher(i);
	}
	
}
