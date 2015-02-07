package autofixture.publicinterface;

import java.util.*;

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

  public static <T, V> SortedMap<T, V> createSortedMapFrom(Class<T> key, Class<V> value) {
    return new TreeMap<>(Generate.manyAsMapOf(key, value));
  }

  public static <T> List<T> createList() {
    return new ArrayList<>();
  }
}
