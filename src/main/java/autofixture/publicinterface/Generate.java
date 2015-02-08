package autofixture.publicinterface;

import autofixture.publicinterface.generators.inline.*;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class Generate {
  public static final String ALL_LETTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
  public static final String ALL_DIGITS = "1029384756";
  private static final Fixture FIXTURE = new Fixture();
  private static final InlineInstanceGenerator<Integer> PORT_NUMBER_GENERATOR = new PortNumberGenerator();
  private static final InlineInstanceGenerator<Character> ALPHA_CHAR_GENERATOR = new CharacterGenerator(
    ALL_LETTERS);
  private static final InlineInstanceGenerator<Character> DIGIT_CHAR_GENERATOR = new CharacterGenerator(
    ALL_DIGITS);

  public static <T> T any(TypeToken<T> instanceType) {
    return FIXTURE.create(instanceType);
  }

  public static <T> T any(Class<T> clazz) {
    return FIXTURE.create(clazz);
  }

  public static <T> T any(TypeToken<T> instanceType, Generate.OtherThanValues<T> omittedValues) {
    return FIXTURE.createWith(new OtherThanGenerator<T>(instanceType, omittedValues.array));
  }

  public static <T> T any(Class<T> instanceType, Generate.OtherThanValues<T> omittedValues) {
    return FIXTURE.createWith(new OtherThanGenerator<T>(TypeToken.of(instanceType), omittedValues.array));
  }

  public static String anyString() {
    return FIXTURE.create(String.class);
  }

  public static String anyStringOfLength(int charactersCount) {
    return FIXTURE.createWith(new StringOfLengthGenerator(charactersCount));
  }

  public static String anyStringNotContaining(String... excludedSubstrings) {
    return FIXTURE.createWith(new StringNotContainingSubstringsGenerator(
      excludedSubstrings));
  }

  public static String anyStringContaining(String str) {
    return FIXTURE.createWith(new StringContainingSubstringGenerator(str));
  }

  public static Character anyAlphaChar() {
    return FIXTURE.createWith(ALPHA_CHAR_GENERATOR);
  }

  public static Character anyDigitChar() {
    return FIXTURE.createWith(DIGIT_CHAR_GENERATOR);
  }

  public static String anyAlphaString() {
    return FIXTURE.createWith(new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
      anyString().length()));
  }

  public static String anyAlphaString(int length) {
    return FIXTURE.createWith(new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
      length));
  }

  public static String anyIdentifier() {
    return FIXTURE.createWith(new IdentifierStringGenerator(
      ALPHA_CHAR_GENERATOR, DIGIT_CHAR_GENERATOR, anyString().length()));
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

  public static Long anyLongOtherThan(long other) {
    return any(new InstanceOf<Long>() {
    }, otherThan(other));
  }

  public static String anyStringOtherThan(String other) {
    return any(new InstanceOf<String>() {
    }, otherThan(other));
  }

  public static Integer anyIntegerOtherThan(int other) {
    return any(new InstanceOf<Integer>() {
    }, otherThan(other));
  }

  public static Short anyShortOtherThan(short other) {
    return any(new InstanceOf<Short>(){}, otherThan(other));
  }

  public static Double anyDoubleOtherThan(double other) {
    return any(new InstanceOf<Double>(){}, otherThan(other));
  }

  public static Float anyFloatOtherThan(float other) {
    return any(new InstanceOf<Float>(){}, otherThan(other));
  }

  public static <T> T anyOf(Class<T> enumClass) {
    return FIXTURE.create(enumClass);
  }

  public static Date anyDate() {
    return FIXTURE.create(Date.class);
  }

  public static <T> T anyExploding(Class<T> clazz) {
    return anyExploding(TypeToken.of(clazz));
  }

  public static <T> T anyExploding(TypeToken<T> instance) {
    return FIXTURE.createWith(new ExplodingInstanceGenerator<>(instance));
  }

  public static Exception anyException() {
    return FIXTURE.create(Exception.class);
  }

  public static Error anyError() {
    return FIXTURE.create(Error.class);
  }

  public static Boolean anyBoolen() {
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
    return FIXTURE.createWith(PORT_NUMBER_GENERATOR);
  }

  public static InetAddress anyIp() {
    return FIXTURE.create(InetAddress.class);
  }

  // ITERABLES - complete

  public static <T> Iterable<T> manyAsIterableOf(InstanceOf<T> type) {
    return FIXTURE.createMany(type);
  }

  public static <T> Iterable<T> manyAsIterableOf(Class<T> clazz) {
    return FIXTURE.createMany(TypeToken.of(clazz));
  }

  public static <T> Iterable<T> manyAsIterableOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues)
  {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Iterable<T> manyAsIterableOf(Class<T> type, Generate.OtherThanValues<T> omittedValues)
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

  public static <T> T[] manyAsArrayOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues)
  {
    Iterable<T> iterable = manyAsIterableOf(typeToken, omittedValues);
    List<T> list = CollectionFactory.createList();
    for (T element : iterable) {
      list.add(element);
    }

    return (T[]) list.toArray();
  }

  public static <T> T[] manyAsArrayOf(Class<T> type, Generate.OtherThanValues<T> omittedValues)
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

  public static <T> List<T> manyAsListOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues) {
    List<T> result = CollectionFactory.createList();
    result.add(any(typeToken, omittedValues));
    result.add(any(typeToken, omittedValues));
    result.add(any(typeToken, omittedValues));

    return result;
  }

  public static <T> List<T> manyAsListOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  // COLLECTIONS - complete

  public static <T> Collection<T> manyAsCollectionOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
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

  private static <T> Set<T> manyAsSetOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    Set<T> collection = CollectionFactory.createSetFrom(many);
    return collection;
  }

  //TODO UT
  public static <T> Set<T> manyAsSetOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsSetOf(TypeToken.of(type), omittedValues);
  }
  //TODO UT
  public static <T> Set<T> manyAsSetOf(TypeToken<T> type, Generate.OtherThanValues<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSetFrom(collection);
  }


  //queues: incomplete
  public static <T> Queue<T> manyAsQueueOf(Class<T> clazz) {
    return manyAsQueueOf(TypeToken.of(clazz));
  }

  private static <T> Queue<T> manyAsQueueOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    Queue<T> queue = CollectionFactory.createQueueFrom(many);
    return queue;
  }

  //TODO UT
  public static <T> Queue<T> manyAsQueueOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsQueueOf(TypeToken.of(type), omittedValues);
  }
  //TODO UT
  public static <T> Queue<T> manyAsQueueOf(TypeToken<T> type, Generate.OtherThanValues<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createQueueFrom(collection);
  }

  //Deques: incomplete
  public static <T> Deque<T> manyAsDequeOf(Class<T> clazz) {
    return manyAsDequeOf(TypeToken.of(clazz));
  }

  private static <T> Deque<T> manyAsDequeOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    Deque<T> collection = CollectionFactory.createDequeFrom(many);
    return collection;
  }

  //TODO UT
  public static <T> Deque<T> manyAsDequeOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsDequeOf(TypeToken.of(type), omittedValues);
  }
  //TODO UT
  public static <T> Deque<T> manyAsDequeOf(TypeToken<T> type, Generate.OtherThanValues<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createDequeFrom(collection);
  }


  //sorted sets: incomplete
  public static <T> SortedSet<T> manyAsSortedSetOf(Class<T> clazz) {
    return manyAsSortedSetOf(TypeToken.of(clazz));
  }

  private static <T> SortedSet<T> manyAsSortedSetOf(TypeToken<T> type) {
    Collection<T> many = FIXTURE.createMany(type);
    SortedSet<T> collection = CollectionFactory.createSortedSetFrom(many);
    return collection;
  }

  //TODO UT
  public static <T> SortedSet<T> manyAsSortedSetOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsSortedSetOf(TypeToken.of(type), omittedValues);
  }

  //TODO UT
  public static <T> SortedSet<T> manyAsSortedSetOf(TypeToken<T> type, Generate.OtherThanValues<T> omittedValues) {
    Collection<T> collection = manyAsCollectionOf(type, omittedValues);
    return CollectionFactory.createSortedSetFrom(collection);
  }

  //sorted maps

  //TODO variations and UT
  public static <T, V> SortedMap<T, V> manyAsSortedMapOf(Class<T> key, Class<V> value) {
    return manyAsSortedMapOf(TypeToken.of(key), TypeToken.of(value));
  }

  public static <T, V> SortedMap<T, V> manyAsSortedMapOf(TypeToken<T> key, TypeToken<V> value) {
    return CollectionFactory.createSortedMapFrom(manyAsMapOf(key, value));
  }

  //maps

  //TODO variations
  public static <T, V> Map<T, V> manyAsMapOf(Class<T> keyClass, Class<V> valueClass) {
    return manyAsMapOf(TypeToken.of(keyClass), TypeToken.of(valueClass));
  }

  private static <T, V> Map<T, V> manyAsMapOf(TypeToken<T> keyType, TypeToken<V> valueType) {
    T[] keys = (T[]) manyAsCollectionOf(keyType).toArray();
    V[] values = (V[]) manyAsCollectionOf(valueType).toArray();

    Map<T, V> map = CollectionFactory.createMapFrom(keys, values);

    return map;
  }

  public static <T> Generate.OtherThanValues<T> otherThan(T... values) {
    return new OtherThanValues(values);
  }

  public static <T> Generate.OtherThanValues<T> without(T... values) {
    return new OtherThanValues(values);
  }

  public static class OtherThanValues<T> {
    public T[] array;

    public OtherThanValues(T... values) {
      this.array = values;
    }
  }


	/*
   * TODO
	 * 
	 * public static T From<T>(params T[] possibleValues) { var
	 * latestArraysWithPossibleValues = _arrayElementPicking.For<T>();
	 * 
	 * if (!latestArraysWithPossibleValues.Contain(possibleValues)) {
	 * latestArraysWithPossibleValues.Add(possibleValues); }
	 * 
	 * var result =
	 * latestArraysWithPossibleValues.PickNextElementFrom(possibleValues);
	 * return result; }
	 * 
	 */
}

