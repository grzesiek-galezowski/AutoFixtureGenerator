package autofixture.publicinterface;

import autofixture.implementationdetails.CollectionFactory;
import autofixture.interfaces.InlineConstrainedGenerator;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.util.*;

import static autofixture.publicinterface.InlineGenerators.exploding;

/**
 * Created by grzes on 01.07.2017.
 */
class PrivateGenerate {
  public static final Fixture FIXTURE = new Fixture();

  static <T> T any(final TypeToken<T> type, final InlineConstrainedGenerator<T> generator) {
    return FIXTURE.create(type, generator);
  }

  static <T> T anyExploding(final TypeToken<T> typeToken) {
    return Any.anonymous(exploding(typeToken));
  }

  static <T> Iterable<T> manyAsIterableOf(
      final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  static <T> T[] manyAsArrayOf(final TypeToken<T> type) {
    return FIXTURE.createArray(type);
  }

  static <T> T[] manyAsArrayOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    final List<T> list = manyAsListOf(typeToken, omittedValues);
    T[] templateArray = CollectionFactory.createArray(typeToken, list.size());
    return list.toArray(templateArray);
  }

  static <T> List<T> manyAsListOf(final TypeToken<T> type) {
    return Lists.newArrayList(FIXTURE.createMany(type));
  }

  //TODO rethink putting this into the Fixture class
  static <T> List<T> manyAsListOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> generator) {
    final List<T> result = CollectionFactory.createList();
    result.add(any(typeToken, generator));
    result.add(any(typeToken, generator));
    result.add(any(typeToken, generator));

    return result;
  }

  private static <T> Collection<T> manyAsCollectionOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  private static <T> Collection<T> manyAsCollectionOf(final TypeToken<T> instanceType) {
    return PrivateGenerate.FIXTURE.createMany(instanceType);
  }

  static <T> Set<T> manyAsSetOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final Set<T> collection = CollectionFactory.createSetFrom(many);
    return collection;
  }

  static <T> Set<T> manyAsSetOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSetFrom(collection);
  }

  static <T> Queue<T> manyAsQueueOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final Queue<T> queue = CollectionFactory.createQueueFrom(many);
    return queue;
  }

  static <T> Queue<T> manyAsQueueOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createQueueFrom(collection);
  }

  static <T> Deque<T> manyAsDequeOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final Deque<T> collection = CollectionFactory.createDequeFrom(many);
    return collection;
  }

  static <T> Deque<T> manyAsDequeOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createDequeFrom(collection);
  }

  static <T> SortedSet<T> manyAsSortedSetOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final SortedSet<T> collection = CollectionFactory.createSortedSetFrom(many);
    return collection;
  }

  static <T> SortedSet<T> manyAsSortedSetOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSortedSetFrom(collection);
  }

  static <T, V> SortedMap<T, V> manyAsSortedMapBetween(final TypeToken<T> key, final TypeToken<V> value) {
    return CollectionFactory.createSortedMapFrom(manyAsMapBetween(key, value));
  }

  static <T, V> Map<T, V> manyAsMapBetween(final TypeToken<T> keyType, final TypeToken<V> valueType) {
    final T[] keys = (T[]) manyAsCollectionOf(keyType).toArray();
    final V[] values = (V[]) manyAsCollectionOf(valueType).toArray();

    final Map<T, V> map = CollectionFactory.createMapFrom(keys, values);

    return map;
  }
}
