package autofixture.publicinterface;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by astral on 07.02.15.
 */
public class CollectionFactory {
  public static <T, V> Map<T, V> createMapFrom(T[] keys, V[] values) {
    Map<T,V> map = new HashMap<T,V>();
    for(int i = 0 ; i < keys.length ; ++i) {
      map.put(keys[i], values[i]);
    }
    return map;
  }

  public static <T> Deque<T> createDequeFrom(Collection<T> many) {
    return new ArrayDeque<T>(many);
  }

  public static <T> SortedSet<T> createSortedSetFrom(Collection<T> many) {
    return new TreeSet<T>(many);
  }

  public static <T> Set<T> createSetFrom(Collection<T> many) {
    return new HashSet<T>(many);
  }

  public static <T, V> SortedMap<T, V> createSortedMapFrom(Map<T, V> map) {
    return new TreeMap<T,V>(map);
  }

  public static <T> List<T> createList() {
    return new ArrayList<>();
  }

  public static ArrayList<Object> createEmptyArrayList() {
    return new ArrayList<>();
  }

  public static Stack<Object> createEmptyStack() {
    return new Stack<>();
  }

  public static ArrayDeque<Object> createEmptyArrayDeque() {
    return new ArrayDeque<>();
  }

  public static ArrayBlockingQueue<Object> createEmptyArrayBlockingQueue(int repeatCount) {
    return new ArrayBlockingQueue<>(repeatCount);
  }

  public static LinkedHashSet<Object> createEmptyLinkedHashSet() {
    return new LinkedHashSet<>();
  }

  public static LinkedList<Object> createEmptyLinkedList() {
    return new LinkedList<>();
  }

  public static ConcurrentLinkedQueue<Object> createEmptyConcurrentLinkedQueue() {
    return new ConcurrentLinkedQueue<>();
  }

  public static ConcurrentSkipListSet<Object> createEmptyConcurrentSkipListSet() {
    return new ConcurrentSkipListSet<>();
  }

  public static CopyOnWriteArrayList<Object> createEmptyCopyOnWriteArrayList() {
    return new CopyOnWriteArrayList<>();
  }

  public static CopyOnWriteArraySet<Object> createEmptyCopyOnWriteArraySet() {
    return new CopyOnWriteArraySet<>();
  }

  public static PriorityBlockingQueue<Object> createEmptyPriorityBlockingQueue() {
    return new PriorityBlockingQueue<>();
  }

  public static PriorityQueue<Object> createEmptyPriorityQueue() {
    return new PriorityQueue<>();
  }

  public static TreeSet<Object> createTreeSet() {
    return new TreeSet<>();
  }

  public static Set<Object> createEmptySet() {
    return new HashSet<>();
  }
}
