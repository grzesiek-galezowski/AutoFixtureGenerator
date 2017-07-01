package autofixture.specification.acceptance;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.matchers.ArrayMatchers;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.concurrent.*;

import static autofixture.publicinterface.Generate.any;
import static autofixture.specification.acceptance.matchers.ArrayMatchers.typeOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class CollectionTypesGenerationSpecification {

  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Collection<String>> collectionClass = new InstanceOf<Collection<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<AbstractCollection<String>> abstractCollectionClass = new InstanceOf<AbstractCollection<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ArrayList<String>> arrayListClass = new InstanceOf<ArrayList<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Set<String>> setClass = new InstanceOf<Set<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<SortedSet<String>> sortedSetClass = new InstanceOf<SortedSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<TreeSet<String>> treeSetClass = new InstanceOf<TreeSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<HashSet<String>> hashSetClass = new InstanceOf<HashSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<PriorityQueue<String>> priorityQueueClass = new InstanceOf<PriorityQueue<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Queue<String>> queueClass = new InstanceOf<Queue<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<NavigableSet<String>> navigableSetClass = new InstanceOf<NavigableSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<AbstractSet<String>> abstractSetClass = new InstanceOf<AbstractSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<AbstractQueue<String>> abstractQueueClass = new InstanceOf<AbstractQueue<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<AbstractList<String>> abstractListClass = new InstanceOf<AbstractList<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<CopyOnWriteArraySet<String>> copyOnWriteArrayClass = new InstanceOf<CopyOnWriteArraySet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ConcurrentSkipListSet<String>> concurrentSkipListSetClass = new InstanceOf<ConcurrentSkipListSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<AbstractSequentialList<String>> abstractSequentialListClass = new InstanceOf<AbstractSequentialList<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<LinkedList<String>> linkedListClass = new InstanceOf<LinkedList<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ArrayBlockingQueue<String>> arrayBlockingQueueClass = new InstanceOf<ArrayBlockingQueue<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ArrayDeque<String>> arrayDequeClass = new InstanceOf<ArrayDeque<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ConcurrentLinkedQueue<String>> concurrentLinkedQueueClass = new InstanceOf<ConcurrentLinkedQueue<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<CopyOnWriteArrayList<String>> copyOnWriteArrayListClass = new InstanceOf<CopyOnWriteArrayList<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<LinkedHashSet<String>> linkedHashSetClass = new InstanceOf<LinkedHashSet<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<PriorityBlockingQueue<String>> priorityBlockingQueueClass = new InstanceOf<PriorityBlockingQueue<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Stack<String>> stackClass = new InstanceOf<Stack<String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Iterable<String>> iterableClass = new InstanceOf<Iterable<String>>() {
  };
  Fixture fixture = new Fixture();

  @Test
  public void shouldGenerateArraysWithThreeUniqueElements() {
    String[] array = fixture.create(InstanceOf.of(String[].class));

    assertThat(array, ArrayMatchers.<String>hasLength(3));
    assertThat(array, ArrayMatchers.<String>hasUniqueItems());
  }

  @Theory
  public void shouldGenerateCollectionsWithThreeUniqueElements(
    InstanceOf<? extends Collection<String>> collectionClass) {
    Collection<String> collection = fixture.create(collectionClass);

    assertHasThreeUniqueItems(collectionClass, collection);
  }

  @Theory
  public void shouldGenerateCollectionsWithThreeUniqueElementsUsingAnyClass(
          InstanceOf<? extends Collection<String>> collectionClass) {
    Collection<String> collection = Any.anonymous(collectionClass);

    assertHasThreeUniqueItems(collectionClass, collection);
  }

  @Theory
  public void shouldGenerateCollectionsWithThreeUniqueElementsUsingAnyMethod(
          InstanceOf<? extends Collection<String>> collectionClass) {
    Collection<String> collection = any(collectionClass);

    assertHasThreeUniqueItems(collectionClass, collection);
  }


  private void assertHasThreeUniqueItems(InstanceOf<? extends Collection<String>> collectionClass, Collection<String> collection) {
    assertTrue("Cannot assign " + collection.getClass() + " to " + collectionClass,
      collectionClass.getRawType().isAssignableFrom(collection.getClass()));

    assertThat(collection.toArray(typeOf(String.class)), ArrayMatchers.<String>hasLength(3));
    assertThat(collection.toArray(typeOf(String.class)), ArrayMatchers.<String>hasUniqueItems());
    System.out.println("OK");
  }



}
