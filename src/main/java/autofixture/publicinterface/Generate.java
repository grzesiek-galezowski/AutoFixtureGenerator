package autofixture.publicinterface;

import autofixture.implementationdetails.Boxing;
import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.interfaces.InlineInstanceGenerator;
import com.google.common.reflect.TypeToken;

import java.util.*;

import static autofixture.publicinterface.InlineGenerators.*;

//todo inline this class
public class Generate {

  public static <T> T any(final InstanceOf<T> instanceType) {
    return PrivateGenerate.FIXTURE.create(instanceType);
  }

  public static <T> T any(final Class<T> clazz) {
    return PrivateGenerate.FIXTURE.create(clazz);
  }

  public static <T> T any(final InlineInstanceGenerator<T> generator) {
    return PrivateGenerate.FIXTURE.create(generator);
  }

  public static <T> T any(final InstanceOf<T> type, final InlineConstrainedGenerator<T> generator) {
    return PrivateGenerate.FIXTURE.create(type, generator);
  }

  public static <T> T any(final Class<T> instanceType, final InlineConstrainedGenerator<T> generator) {
    return PrivateGenerate.any(TypeToken.of(instanceType), generator);
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
  public static String anyLowercaseString() {
    return any(lowercaseString());
  }

  public static String anyUppercaseString() {
    return any(uppercaseString());
  }

  public static String anyLowercaseString(final int length) {
    return any(lowercaseString(length));
  }

  public static String anyUppercaseString(final int length) {
    return any(uppercaseString(length));
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

  public static Integer anyInteger() {
    return PrivateGenerate.FIXTURE.create(int.class);
  }

  public static Short anyShort() {
    return PrivateGenerate.FIXTURE.create(short.class);
  }

  public static Double anyDouble() {
    return PrivateGenerate.FIXTURE.create(double.class);
  }

  public static Float anyFloat() {
    return PrivateGenerate.FIXTURE.create(float.class);
  }

  public static Character anyChar() {
    return PrivateGenerate.FIXTURE.create(char.class);
  }

  public static Long anyLong() {
    return PrivateGenerate.FIXTURE.create(long.class);
  }

  public static Long anyLongOtherThan(final long... other) {
    return any(new InstanceOf<Long>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static String anyStringOtherThan(final String... other) {
    return any(new InstanceOf<String>() {
    }, InlineGenerators.otherThan(other));
  }

  public static Integer anyIntegerOtherThan(final int... other) {
    return any(new InstanceOf<Integer>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static Short anyShortOtherThan(final short... other) {
    return any(new InstanceOf<Short>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static Double anyDoubleOtherThan(final double... other) {
    return any(new InstanceOf<Double>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static Float anyFloatOtherThan(final float... other) {
    return any(new InstanceOf<Float>() {
    }, InlineGenerators.otherThan(Boxing.boxed(other)));
  }

  public static <T> T anyOf(final Class<T> enumClass) {
    return any(enumClass);
  }

  public static Date anyDate() {
    return any(Date.class);
  }

  public static <T> T anyExploding(final Class<T> clazz) {
    return PrivateGenerate.anyExploding(TypeToken.of(clazz));
  }

  public static <T> T anyExploding(final InstanceOf<T> typeToken) {
    return PrivateGenerate.anyExploding(typeToken);
  }

  public static int anyPort() {
    return PrivateGenerate.FIXTURE.create(portNumber());
  }

  private static final HashMap<Class, Integer> INDICES_BY_CLASS = new HashMap<>();

  //todo extract to inline generator
  public static <T> T anyFrom(final T... possibleValues) {
    final Class<?> key = possibleValues[0].getClass();
    if (!INDICES_BY_CLASS.containsKey(key)) {
      INDICES_BY_CLASS.put(key, new Random().nextInt(possibleValues.length));
    }

    Integer index = INDICES_BY_CLASS.get(key);

    if (index >= possibleValues.length) {
      index = 0;
    }

    final T returnedValue = possibleValues[index];

    index++;
    INDICES_BY_CLASS.put(key, index);

    return returnedValue;
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


  public static String anyString(String seed) {
    return seed + anyString();
  }
}

