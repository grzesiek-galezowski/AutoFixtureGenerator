package autofixture.publicinterface;

import autofixture.implementationdetails.Boxing;
import autofixture.interfaces.InlineConstrainedGenerator;
import com.google.common.reflect.TypeToken;

import java.util.*;

import static autofixture.publicinterface.InlineGenerators.*;

//todo inline this class
public class Generate {

  public static <T> T any(final Class<T> instanceType, final InlineConstrainedGenerator<T> generator) {
    return PrivateGenerate.any(TypeToken.of(instanceType), generator);
  }

  public static Long anyLongOtherThan(final long... others) {
    return Any.longOtherThan(others);
  }

  public static String anyStringOtherThan(final String... others) {
    return Any.stringOtherThan(others);
  }

  public static Integer anyIntegerOtherThan(final int... others) {
    return Any.intOtherThan(others);
  }

  public static Short anyShortOtherThan(final short... other) {
    return PrivateGenerate.FIXTURE.create(new InstanceOf<Short>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static Double anyDoubleOtherThan(final double... other) {
    return PrivateGenerate.FIXTURE.create(new InstanceOf<Double>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static Float anyFloatOtherThan(final float... other) {
    return PrivateGenerate.FIXTURE.create(new InstanceOf<Float>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static <T> T anyExploding(final Class<T> clazz) {
    return PrivateGenerate.anyExploding(TypeToken.of(clazz));
  }

  public static <T> T anyExploding(final InstanceOf<T> typeToken) {
    return PrivateGenerate.anyExploding(typeToken);
  }

  // ITERABLES - complete

  public static <T> Iterable<T> manyAsIterableOf(final InstanceOf<T> type) {
    return PrivateGenerate.FIXTURE.createMany(type);
  }

  public static <T> Iterable<T> manyAsIterableOf(final Class<T> clazz) {
    return PrivateGenerate.FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Iterable<T> manyAsIterableOf(
      final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsIterableOf(typeToken, omittedValues);
  }


  public static <T> Iterable<T> manyAsIterableOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsIterableOf(TypeToken.of(type), omittedValues);
  }

  //ARRAYS - complete
  public static <T> T[] manyAsArrayOf(final Class<T> clazz) {
    return PrivateGenerate.manyAsArrayOf(TypeToken.of(clazz));
  }

  public static <T> T[] manyAsArrayOf(final InstanceOf<T> type) {
    return PrivateGenerate.manyAsArrayOf(type);
  }

  public static <T> T[] manyAsArrayOf(final InstanceOf<T> typeToken,
                                       final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsArrayOf(typeToken, omittedValues);
  }

  public static <T> T[] manyAsArrayOf(final Class<T> type,
                                      final InlineConstrainedGenerator<T> omittedValues) {
    T[] array = PrivateGenerate.manyAsArrayOf(TypeToken.of(type), omittedValues);
    return array;
  }

  //LISTS - complete
  public static <T> List<T> manyAsListOf(final Class<T> clazz) {
    return PrivateGenerate.manyAsListOf(TypeToken.of(clazz));
  }

  public static <T> List<T> manyAsListOf(final InstanceOf<T> type) {
    return PrivateGenerate.manyAsListOf(type);
  }

  public static <T> List<T> manyAsListOf(final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> generator) {
    return PrivateGenerate.manyAsListOf(typeToken, generator);
  }

  public static <T> List<T> manyAsListOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsListOf(TypeToken.of(type), omittedValues);
  }

  // COLLECTIONS - complete

  public static <T> Collection<T> manyAsCollectionOf(final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsListOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(final Class<T> clazz) {
    return PrivateGenerate.FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Collection<T> manyAsCollectionOf(final InstanceOf<T> instanceType) {
    return PrivateGenerate.FIXTURE.createMany(instanceType);
  }

  //SETS: complete
  public static <T> Set<T> manyAsSetOf(final Class<T> clazz) {
    return PrivateGenerate.manyAsSetOf(TypeToken.of(clazz));
  }

  public static <T> Set<T> manyAsSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsSetOf(TypeToken.of(type), omittedValues);
  }

  //queues: complete
  public static <T> Queue<T> manyAsQueueOf(final Class<T> clazz) {
    return PrivateGenerate.manyAsQueueOf(TypeToken.of(clazz));
  }


  public static <T> Queue<T> manyAsQueueOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsQueueOf(TypeToken.of(type), omittedValues);
  }

  //Deques: complete
  public static <T> Deque<T> manyAsDequeOf(final Class<T> clazz) {
    return PrivateGenerate.manyAsDequeOf(TypeToken.of(clazz));
  }

  public static <T> Deque<T> manyAsDequeOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsDequeOf(TypeToken.of(type), omittedValues);
  }

  //sorted sets: complete
  public static <T> SortedSet<T> manyAsSortedSetOf(final Class<T> clazz) {
    return PrivateGenerate.manyAsSortedSetOf(TypeToken.of(clazz));
  }


  public static <T> SortedSet<T> manyAsSortedSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return PrivateGenerate.manyAsSortedSetOf(TypeToken.of(type), omittedValues);
  }

  //sorted maps

  //TODO variations and UT
  public static <T, V> SortedMap<T, V> manyAsSortedMapBetween(final Class<T> key, final Class<V> value) {
    return PrivateGenerate.manyAsSortedMapBetween(TypeToken.of(key), TypeToken.of(value));
  }

  //maps

  //TODO variations
  public static <T, V> Map<T, V> manyAsMapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    return PrivateGenerate.manyAsMapBetween(TypeToken.of(keyClass), TypeToken.of(valueClass));
  }
}

