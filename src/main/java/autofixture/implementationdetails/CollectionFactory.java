package autofixture.implementationdetails;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by astral on 07.02.15.
 */
public class CollectionFactory {
  public static <T, V> Map<T, V> createMapFrom(final T[] keys, final V[] values) {
    final Map<T, V> map = new HashMap<>();
    for (int i = 0; i < keys.length; ++i) {
      map.put(keys[i], values[i]);
    }
    return map;
  }

  public static <T> Deque<T> createDequeFrom(final Collection<T> many) {
    return new ArrayDeque<>(many);
  }

  public static <T> SortedSet<T> createSortedSetFrom(final Collection<T> many) {
    return new TreeSet<>(many);
  }

  public static <T> Set<T> createSetFrom(final Collection<T> many) {
    return new HashSet<>(many);
  }

  public static <T, V> SortedMap<T, V> createSortedMapFrom(final Map<T, V> map) {
    return new TreeMap<>(map);
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

  public static ArrayBlockingQueue<Object> createEmptyArrayBlockingQueue(final int repeatCount) {
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

  public static <T> PriorityQueue<T> createQueueFrom(final Collection<T> many) {
    return new PriorityQueue<>(many);
  }

  public static <T> T[] createArray(TypeToken<T> type, int length) {
    return (T[]) Array.newInstance(
      type.getRawType(), length);
  }

  public static Map createEmptyHashMap() {
    return new HashMap<>();
  }

  public static Map createEmptySortedMap() {
    return new TreeMap<>();
  }

  public static Map createEmptyTreeMap() {
    return new TreeMap<>();
  }

  public static Map createEmptyNavigableMap() {
    return new TreeMap<>();
  }

  public static Map createEmptyConcurrentHashMap() {
    return new ConcurrentHashMap<>();
  }

  public static Map createEmptyConcurrentSkipListMap() {
    return new ConcurrentSkipListMap<>();
  }

  public static Map createEmptyHashtable() {
    return new Hashtable<>();
  }

  public static Map createEmptyLinkedHashMap() {
    return new LinkedHashMap<>();
  }

  public static Map createEmptyWeakHashMap() {
    return new WeakHashMap<>();
  }

  public static Map createEmptyIdentityHashMap() {
    return new IdentityHashMap<>();
  }

  public static Map createEmptyEnumMap(Class<Enum> keyType) {
    return new EnumMap(keyType);
  }
}
