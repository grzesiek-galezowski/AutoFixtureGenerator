package autofixture.publicinterface;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static autofixture.publicinterface.InlineGenerators.alphaChar;
import static autofixture.publicinterface.InlineGenerators.alphaString;
import static autofixture.publicinterface.InlineGenerators.digitChar;
import static autofixture.publicinterface.InlineGenerators.exploding;
import static autofixture.publicinterface.InlineGenerators.identifierString;
import static autofixture.publicinterface.InlineGenerators.portNumber;
import static autofixture.publicinterface.InlineGenerators.stringContaining;
import static autofixture.publicinterface.InlineGenerators.stringNotContaining;
import static autofixture.publicinterface.InlineGenerators.stringOfLength;

public class Generate {
  private static final Fixture FIXTURE = new Fixture();

  public static <T> T any(final TypeToken<T> instanceType) {
    return FIXTURE.create(instanceType);
  }

  public static <T> T any(final Class<T> clazz) {
    return FIXTURE.create(clazz);
  }

  public static <T> T any(final InlineInstanceGenerator<T> generator) {
    return FIXTURE.create(generator);
  }

  public static <T> T any(final TypeToken<T> type, final InlineConstrainedGenerator<T> generator) {
    return FIXTURE.create(type, generator);
  }

  public static <T> T any(final Class<T> instanceType, final InlineConstrainedGenerator<T> generator) {

    return any(TypeToken.of(instanceType), generator);
  }

  public static String anyString() {
    return any(String.class);
  }

  public static String anyStringOfLength(final int charactersCount) {
    return any(stringOfLength(charactersCount));
  }

  public static String anyStringNotContaining(final String... excludedSubstrings) {
    return any(stringNotContaining(excludedSubstrings));
  }

  public static String anyStringContaining(final String str) {
    return any(stringContaining(str));
  }

  public static Character anyAlphaChar() {
    return any(alphaChar());
  }

  public static Character anyDigitChar() {
    return any(digitChar());
  }

  public static String anyAlphaString() {
    return any(alphaString());
  }

  public static String anyAlphaString(final int length) {
    return any(alphaString(length));
  }

  public static String anyIdentifier() {
    return any(identifierString());
  }

  public static String anyLegalXmlTagName() {
    return anyIdentifier();
  }

  public static Integer anyInteger() {
    return FIXTURE.create(int.class);
  }

  public static Short anyShort() {
    return FIXTURE.create(short.class);
  }

  public static Double anyDouble() {
    return FIXTURE.create(double.class);
  }

  public static Float anyFloat() {
    return FIXTURE.create(float.class);
  }

  public static Character anyChar() {
    return FIXTURE.create(char.class);
  }

  public static Long anyLong() {
    return FIXTURE.create(long.class);
  }

  public static Long anyLongOtherThan(final long... other) {
    return any(new InstanceOf<Long>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static String anyStringOtherThan(final String... other) {
    return any(new InstanceOf<String>() {
    }, InlineGenerators.otherThan(other));
  }

  public static Integer anyIntegerOtherThan(final int... other) {
    return any(new InstanceOf<Integer>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static Short anyShortOtherThan(final short... other) {
    return any(new InstanceOf<Short>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static Double anyDoubleOtherThan(final double... other) {
    return any(new InstanceOf<Double>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static Float anyFloatOtherThan(final float... other) {
    return any(new InstanceOf<Float>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static <T> T anyOf(final Class<T> enumClass) {
    return any(enumClass);
  }

  public static Date anyDate() {
    return any(Date.class);
  }

  public static <T> T anyExploding(final Class<T> clazz) {
    return anyExploding(TypeToken.of(clazz));
  }

  public static <T> T anyExploding(final TypeToken<T> typeToken) {
    return any(exploding(typeToken));
  }

  public static Exception anyException() {
    return any(RuntimeException.class);
  }

  public static Error anyError() {
    return FIXTURE.create(Error.class);
  }

  public static Boolean anyBoolean() {
    return FIXTURE.create(Boolean.class);
  }

  public static Object anyObject() {
    return FIXTURE.create(Object.class);
  }

  public static URI anyUri() {
    return FIXTURE.create(URI.class);
  }

  public static URL anyUrl() {
    return FIXTURE.create(URL.class);
  }

  public static int anyPort() {
    return FIXTURE.create(portNumber());
  }

  public static InetAddress anyIp() {
    return FIXTURE.create(InetAddress.class);
  }

  public static ChronoLocalDate anyChronoLocalDate() {
    return FIXTURE.create(ChronoLocalDate.class);
  }

  public static ChronoLocalDateTime anyChronoLocalDateTime() {
    return FIXTURE.create(ChronoLocalDateTime.class);
  }

  public static LocalDateTime anyLocalDateTime() {
    return FIXTURE.create(LocalDateTime.class);
  }

  public static LocalDate anyLocalDate() {
    return FIXTURE.create(LocalDate.class);
  }

  public static ZonedDateTime anyZonedDateTime() {
    return FIXTURE.create(ZonedDateTime.class);
  }

  public static ZoneId anyZoneId() {
    return FIXTURE.create(ZoneId.class);
  }

  public static OffsetTime anyOffsetTime() {
    return FIXTURE.create(OffsetTime.class);
  }

  public static Period anyPeriod() {
    return FIXTURE.create(Period.class);
  }

  public static Duration anyDuration() {
    return FIXTURE.create(Duration.class);
  }

  public static ZoneOffset anyZoneOffset() {
    return FIXTURE.create(ZoneOffset.class);
  }

  public static Clock anyClock() {
    return FIXTURE.create(Clock.class);
  }

  public static Instant anyInstant() {
    return FIXTURE.create(Instant.class);
  }

  private static final HashMap<Class, Integer> indices = new HashMap<>();

  public static <T> T anyFrom(final T... possibleValues) {
    final Class<?> key = possibleValues[0].getClass();
    if (!indices.containsKey(key)) {
      indices.put(key, new Random().nextInt(possibleValues.length));
    }

    Integer index = indices.get(key);

    if (index >= possibleValues.length) {
      index = 0;
    }

    final T returnedValue = possibleValues[index];

    index++;
    indices.put(key, index);

    return returnedValue;
  }

  // ITERABLES - complete


  public static <T> Iterable<T> manyAsIterableOf(final InstanceOf<T> type) {
    return FIXTURE.createMany(type);
  }

  public static <T> Iterable<T> manyAsIterableOf(final Class<T> clazz) {
    return FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Iterable<T> manyAsIterableOf(
      final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Iterable<T> manyAsIterableOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsIterableOf(TypeToken.of(type), omittedValues);
  }

  //ARRAYS - complete
  public static <T> T[] manyAsArrayOf(final Class<T> clazz) {
    return manyAsArrayOf(TypeToken.of(clazz));
  }

  public static <T> T[] manyAsArrayOf(final TypeToken<T> type) {

    return FIXTURE.createArray(type);
  }

  public static <T> T[] manyAsArrayOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    final List<T> list = manyAsListOf(typeToken, omittedValues);
    T[] templateArray = CollectionFactory.createArray(typeToken, list.size());
    return list.toArray(templateArray);
  }

  public static <T> T[] manyAsArrayOf(final Class<T> type,
                                      final InlineConstrainedGenerator<T> omittedValues) {
    T[] array = manyAsArrayOf(TypeToken.of(type), omittedValues);
    return array;
  }

  //LISTS - complete

  public static <T> List<T> manyAsListOf(final Class<T> clazz) {
    return Lists.newArrayList(FIXTURE.createMany(TypeToken.of(clazz)));
  }

  public static <T> List<T> manyAsListOf(final InstanceOf<T> type) {
    return Lists.newArrayList(FIXTURE.createMany(type));
  }

  //TODO rethink putting this into the Fixture class
  public static <T> List<T> manyAsListOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> generator) {
    final List<T> result = CollectionFactory.createList();
    result.add(any(typeToken, generator));
    result.add(any(typeToken, generator));
    result.add(any(typeToken, generator));

    return result;
  }

  public static <T> List<T> manyAsListOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  // COLLECTIONS - complete

  public static <T> Collection<T> manyAsCollectionOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(final Class<T> clazz) {
    return FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Collection<T> manyAsCollectionOf(final TypeToken<T> instanceType) {
    return FIXTURE.createMany(instanceType);
  }

  //SETS: complete

  public static <T> Set<T> manyAsSetOf(final Class<T> clazz) {
    return manyAsSetOf(TypeToken.of(clazz));
  }

  public static <T> Set<T> manyAsSetOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final Set<T> collection = CollectionFactory.createSetFrom(many);
    return collection;
  }

  public static <T> Set<T> manyAsSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsSetOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Set<T> manyAsSetOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSetFrom(collection);
  }

  //queues: complete
  public static <T> Queue<T> manyAsQueueOf(final Class<T> clazz) {
    return manyAsQueueOf(TypeToken.of(clazz));
  }


  public static <T> Queue<T> manyAsQueueOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final Queue<T> queue = CollectionFactory.createQueueFrom(many);
    return queue;
  }

  public static <T> Queue<T> manyAsQueueOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsQueueOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Queue<T> manyAsQueueOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createQueueFrom(collection);
  }

  //Deques: complete
  public static <T> Deque<T> manyAsDequeOf(final Class<T> clazz) {
    return manyAsDequeOf(TypeToken.of(clazz));
  }

  public static <T> Deque<T> manyAsDequeOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final Deque<T> collection = CollectionFactory.createDequeFrom(many);
    return collection;
  }

  public static <T> Deque<T> manyAsDequeOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsDequeOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Deque<T> manyAsDequeOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createDequeFrom(collection);
  }

  //sorted sets: complete
  public static <T> SortedSet<T> manyAsSortedSetOf(final Class<T> clazz) {
    return manyAsSortedSetOf(TypeToken.of(clazz));
  }


  public static <T> SortedSet<T> manyAsSortedSetOf(final TypeToken<T> type) {
    final Collection<T> many = FIXTURE.createMany(type);
    final SortedSet<T> collection = CollectionFactory.createSortedSetFrom(many);
    return collection;
  }

  public static <T> SortedSet<T> manyAsSortedSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return manyAsSortedSetOf(TypeToken.of(type), omittedValues);
  }

  public static <T> SortedSet<T> manyAsSortedSetOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    final Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSortedSetFrom(collection);
  }

  //sorted maps

  //TODO variations and UT
  public static <T, V> SortedMap<T, V> manyAsSortedMapBetween(final Class<T> key, final Class<V> value) {
    return manyAsSortedMapBetween(TypeToken.of(key), TypeToken.of(value));
  }

  public static <T, V> SortedMap<T, V> manyAsSortedMapBetween(final TypeToken<T> key, final TypeToken<V> value) {
    return CollectionFactory.createSortedMapFrom(manyAsMapBetween(key, value));
  }

  //maps

  //TODO variations
  public static <T, V> Map<T, V> manyAsMapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    return manyAsMapBetween(TypeToken.of(keyClass), TypeToken.of(valueClass));
  }

  public static <T, V> Map<T, V> manyAsMapBetween(final TypeToken<T> keyType, final TypeToken<V> valueType) {
    final T[] keys = (T[]) manyAsCollectionOf(keyType).toArray();
    final V[] values = (V[]) manyAsCollectionOf(valueType).toArray();

    final Map<T, V> map = CollectionFactory.createMapFrom(keys, values);

    return map;
  }

  private static Long[] boxed(final long[] other) {
    return LongStream.of(other).boxed().toArray(Long[]::new);
  }

  private static Float[] boxed(final float[] other) {
    final ArrayList<Float> list = new ArrayList<>();
    for (final float f : other) {
      list.add(f);
    }
    return list.toArray(new Float[]{});
  }

  private static Short[] boxed(final short[] other) {
    final ArrayList<Short> list = new ArrayList<>();
    for (final short s : other) {
      list.add(s);
    }
    return list.toArray(new Short[]{});
  }

  private static Double[] boxed(final double[] other) {
    return DoubleStream.of(other).boxed().toArray(Double[]::new);
  }

  private static Integer[] boxed(final int... other) {
    return IntStream.of(other).boxed().toArray(Integer[]::new);
  }


}

