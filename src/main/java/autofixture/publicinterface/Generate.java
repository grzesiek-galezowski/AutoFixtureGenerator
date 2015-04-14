package autofixture.publicinterface;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static autofixture.publicinterface.InlineGenerators.*;

public class Generate {
  private static final Fixture FIXTURE = new Fixture();

  public static <T> T any(TypeToken<T> instanceType) {
    return FIXTURE.create(instanceType);
  }

  public static <T> T any(Class<T> clazz) {
    return FIXTURE.create(clazz);
  }

  public static <T> T any(InlineInstanceGenerator<T> generator) {
    return FIXTURE.create(generator);
  }

  public static <T> T any(TypeToken<T> type, InlineConstrainedGenerator<T> generator) {
    return FIXTURE.create(type, generator);
  }

  public static <T> T any(Class<T> instanceType, InlineConstrainedGenerator<T> generator) {

    return any(TypeToken.of(instanceType), generator);
  }

  public static String anyString() {
    return any(String.class);
  }

  public static String anyStringOfLength(int charactersCount) {
    return any(stringOfLength(charactersCount));
  }

  public static String anyStringNotContaining(String... excludedSubstrings) {
    return any(stringNotContaining(excludedSubstrings));
  }

  public static String anyStringContaining(String str) {
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

  public static String anyAlphaString(int length) {
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

  public static Long anyLongOtherThan(long... other) {
    return any(new InstanceOf<Long>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static String anyStringOtherThan(String... other) {
    return any(new InstanceOf<String>() {
    }, InlineGenerators.otherThan(other));
  }

  public static Integer anyIntegerOtherThan(int... other) {
    return any(new InstanceOf<Integer>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static Short anyShortOtherThan(short... other) {
    return any(new InstanceOf<Short>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static Double anyDoubleOtherThan(double... other) {
    return any(new InstanceOf<Double>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static Float anyFloatOtherThan(float... other) {
    return any(new InstanceOf<Float>() {
    }, InlineGenerators.otherThan(boxed(other)));
  }

  public static <T> T anyOf(Class<T> enumClass) {
    return any(enumClass);
  }

  public static Date anyDate() {
    return any(Date.class);
  }

  public static <T> T anyExploding(Class<T> clazz) {
    return anyExploding(TypeToken.of(clazz));
  }

  public static <T> T anyExploding(TypeToken<T> typeToken) {
    return any(exploding(typeToken));
  }

  public static Exception anyException() {
    return any(Exception.class);
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

  private static HashMap<Class, Integer> indices = new HashMap<>();

  public static <T> T anyFrom(T... possibleValues) {
    Class<?> key = possibleValues[0].getClass();
    if(!indices.containsKey(key)) {
      indices.put(key, new Random().nextInt(possibleValues.length));
    }

    Integer index = indices.get(key);

    if(index >= possibleValues.length) {
      index = 0;
    }

    T returnedValue = possibleValues[index];

    index++;
    indices.put(key, index);

    return returnedValue;
  }

  // ITERABLES - complete


  public static <T> Iterable<T> manyAsIterableOf(InstanceOf<T> type) {
    return FIXTURE.createMany(type);
  }

  public static <T> Iterable<T> manyAsIterableOf(Class<T> clazz) {
    return FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Iterable<T> manyAsIterableOf(
    TypeToken<T> typeToken, InlineConstrainedGenerator<T> omittedValues)
  {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Iterable<T> manyAsIterableOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues)
  {
    return manyAsIterableOf(TypeToken.of(type), omittedValues);
  }

  //ARRAYS - complete
  public static <T> T[] manyAsArrayOf(Class<T> clazz) {
    return (T[]) FIXTURE.createMany(TypeToken.of(clazz)).toArray();
  }

  public static <T> T[] manyAsArrayOf(InstanceOf<T> type) {
    return (T[]) FIXTURE.createMany(type).toArray();
  }

  public static <T> T[] manyAsArrayOf(TypeToken<T> typeToken, InlineConstrainedGenerator<T> omittedValues)
  {
    Iterable<T> iterable = manyAsIterableOf(typeToken, omittedValues);
    List<T> list = CollectionFactory.createList();
    for (T element : iterable) {
      list.add(element);
    }

    return (T[]) list.toArray();
  }

  public static <T> T[] manyAsArrayOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues)
  {
    return manyAsArrayOf(TypeToken.of(type), omittedValues);
  }

  //LISTS - complete

  public static <T> List<T> manyAsListOf(Class<T> clazz) {
    return Lists.newArrayList(FIXTURE.createMany(TypeToken.of(clazz)));
  }

  public static <T> List<T> manyAsListOf(InstanceOf<T> type) {
    return Lists.newArrayList(FIXTURE.createMany(type));
  }

  public static <T> List<T> manyAsListOf(TypeToken<T> typeToken, InlineConstrainedGenerator<T> generator) {
    List<T> result = CollectionFactory.createList();
    result.add(any(typeToken, generator));
    result.add(any(typeToken, generator));
    result.add(any(typeToken, generator));

    return result;
  }

  public static <T> List<T> manyAsListOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  // COLLECTIONS - complete

  public static <T> Collection<T> manyAsCollectionOf(TypeToken<T> typeToken, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(Class<T> clazz) {
    return FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Collection<T> manyAsCollectionOf(TypeToken<T> instanceType) {
    return FIXTURE.createMany(instanceType);
  }

  //SETS: incomplete

  //TODO variations
  public static <T> Set<T> manyAsSetOf(Class<T> clazz) {
    return manyAsSetOf(TypeToken.of(clazz));
  }

  public static <T> Set<T> manyAsSetOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    Set<T> collection = CollectionFactory.createSetFrom(many);
    return collection;
  }

  //TODO UT
  public static <T> Set<T> manyAsSetOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsSetOf(TypeToken.of(type), omittedValues);
  }

  //TODO UT
  public static <T> Set<T> manyAsSetOf(TypeToken<T> type,InlineConstrainedGenerator<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSetFrom(collection);
  }
  //queues: incomplete
  public static <T> Queue<T> manyAsQueueOf(Class<T> clazz) {
    return manyAsQueueOf(TypeToken.of(clazz));
  }


  public static <T> Queue<T> manyAsQueueOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    Queue<T> queue = CollectionFactory.createQueueFrom(many);
    return queue;
  }

  //TODO UT
  public static <T> Queue<T> manyAsQueueOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsQueueOf(TypeToken.of(type), omittedValues);
  }

  //TODO UT
  public static <T> Queue<T> manyAsQueueOf(TypeToken<T> type, InlineConstrainedGenerator<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createQueueFrom(collection);
  }
  //Deques: incomplete
  public static <T> Deque<T> manyAsDequeOf(Class<T> clazz) {
    return manyAsDequeOf(TypeToken.of(clazz));
  }

  public static <T> Deque<T> manyAsDequeOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    Deque<T> collection = CollectionFactory.createDequeFrom(many);
    return collection;
  }

  //TODO UT
  public static <T> Deque<T> manyAsDequeOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsDequeOf(TypeToken.of(type), omittedValues);
  }

  //TODO UT
  public static <T> Deque<T> manyAsDequeOf(TypeToken<T> type, InlineConstrainedGenerator<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createDequeFrom(collection);
  }
  //sorted sets: incomplete
  public static <T> SortedSet<T> manyAsSortedSetOf(Class<T> clazz) {
    return manyAsSortedSetOf(TypeToken.of(clazz));
  }


  public static <T> SortedSet<T> manyAsSortedSetOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    SortedSet<T> collection = CollectionFactory.createSortedSetFrom(many);
    return collection;
  }

  //TODO UT
  public static <T> SortedSet<T> manyAsSortedSetOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return manyAsSortedSetOf(TypeToken.of(type), omittedValues);
  }

  //TODO UT
  public static <T> SortedSet<T> manyAsSortedSetOf(TypeToken<T> type, InlineConstrainedGenerator<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSortedSetFrom(collection);
  }

  //sorted maps

  //TODO variations and UT
  public static <T, V> SortedMap<T, V> manyAsSortedMapBetween(Class<T> key, Class<V> value) {
    return manyAsSortedMapBetween(TypeToken.of(key), TypeToken.of(value));
  }

  public static <T, V> SortedMap<T, V> manyAsSortedMapBetween(TypeToken<T> key, TypeToken<V> value) {
    return CollectionFactory.createSortedMapFrom(manyAsMapBetween(key, value));
  }

  //maps

  //TODO variations
  public static <T, V> Map<T, V> manyAsMapBetween(Class<T> keyClass, Class<V> valueClass) {
    return manyAsMapBetween(TypeToken.of(keyClass), TypeToken.of(valueClass));
  }

  public static <T, V> Map<T, V> manyAsMapBetween(TypeToken<T> keyType, TypeToken<V> valueType) {
    T[] keys = (T[]) manyAsCollectionOf(keyType).toArray();
    V[] values = (V[]) manyAsCollectionOf(valueType).toArray();

    Map<T, V> map = CollectionFactory.createMapFrom(keys, values);

    return map;
  }

  private static Long[] boxed(long[] other) {
    return LongStream.of(other).boxed().toArray(Long[]::new);
  }

  private static Float[] boxed(float[] other) {
    ArrayList<Float> list = new ArrayList<>();
    for(float f : other) {
      list.add(f);
    }
    return list.toArray(new Float[]{});
  }

  private static Short[] boxed(short[] other) {
    ArrayList<Short> list = new ArrayList<>();
    for(short s : other) {
      list.add(s);
    }
    return list.toArray(new Short[]{});
  }

  private static Double[] boxed(double[] other) {
    return DoubleStream.of(other).boxed().toArray(Double[]::new);
  }

  private static Integer[] boxed(int... other) {
    return IntStream.of(other).boxed().toArray(Integer[]::new);
  }


}

