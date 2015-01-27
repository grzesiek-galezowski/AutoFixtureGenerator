package autofixture.publicinterface;

import autofixture.publicinterface.generators.inline.*;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class Generate {
  private static final String AllLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
  private static final String AllDigits = "1029384756";
  private static final Fixture fixture = new Fixture();
  private static final InlineInstanceGenerator<Integer> portNumberGenerator = new PortNumberGenerator();
  private static final InlineInstanceGenerator<Character> alphaCharGenerator = new CharacterGenerator(
    AllLetters);
  private static final InlineInstanceGenerator<Character> digitCharGenerator = new CharacterGenerator(
    AllDigits);

  public static <T> T any(TypeToken<T> instanceType) {
    return fixture.create(instanceType);
  }

  public static <T> T any(Class<T> clazz) {
    return fixture.create(clazz);
  }

  public static <T> T any(TypeToken<T> instanceType, Generate.OtherThanValues<T> omittedValues) {
    return fixture.createWith(new OtherThanGenerator<T>(instanceType, omittedValues.array));
  }

  public static <T> T any(Class<T> instanceType, Generate.OtherThanValues<T> omittedValues) {
    return fixture.createWith(new OtherThanGenerator<T>(TypeToken.of(instanceType), omittedValues.array));
  }


  public static String anyString() {
    return fixture.create(String.class);
  }

  public static String anyStringOfLength(int charactersCount) {
    return fixture.createWith(new StringOfLengthGenerator(charactersCount));
  }

  public static String anyStringNotContaining(String... excludedSubstrings) {
    return fixture.createWith(new StringNotContainingSubstringsGenerator(
      excludedSubstrings));
  }

  public static String anyStringContaining(String str) {
    return fixture.createWith(new StringContainingSubstringGenerator(str));
  }

  public static Character anyAlphaChar() {
    return fixture.createWith(alphaCharGenerator);
  }

  public static Character anyDigitChar() {
    return fixture.createWith(digitCharGenerator);
  }

  public static String anyAlphaString() {
    return fixture.createWith(new AlphaStringGenerator(alphaCharGenerator,
      anyString().length()));
  }

  public static String anyAlphaString(int length) {
    return fixture.createWith(new AlphaStringGenerator(alphaCharGenerator,
      length));
  }

  public static String anyIdentifier() {
    return fixture.createWith(new IdentifierStringGenerator(
      alphaCharGenerator, digitCharGenerator, anyString().length()));
  }

  public static String anyLegalXmlTagName() {
    return anyIdentifier();
  }

  public static Integer anyInteger() {
    return fixture.create(int.class);
  }

  public static Short anyShort() {
    return fixture.create(short.class);
  }

  public static Double anyDouble() {
    return fixture.create(double.class);
  }

  public static Float anyFloat() {
    return fixture.create(float.class);
  }

  public static Character anyChar() {
    return fixture.create(char.class);
  }

  public static Long anyLong() {
    return fixture.create(long.class);
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
    return fixture.create(enumClass);
  }

  public static Date anyDate() {
    return fixture.create(Date.class);
  }

  //bug this will not work
  public static <T> T anyExploding(Class<T> clazz) {
    return anyExploding(new InstanceOf<T>());
  }

  public static <T> T anyExploding(TypeToken<T> instance) {
    return fixture.createWith(new ExplodingInstanceGenerator<>(instance));
  }

  public static Exception anyException() {
    return fixture.create(Exception.class);
  }

  public static Error anyError() {
    return fixture.create(Error.class);
  }

  public static Boolean anyBoolen() {
    return fixture.create(Boolean.class);
  }

  public static Object anyObject() {
    return fixture.create(Object.class);
  }

  public static URI anyUri() {
    return fixture.create(URI.class);
  }

  public static URL anyUrl() {
    return fixture.create(URL.class);
  }

  public static int anyPort() {
    return fixture.createWith(portNumberGenerator);
  }

  public static InetAddress anyIp() {
    return fixture.create(InetAddress.class);
  }

  // ITERABLES

  public static <T> Iterable<T> manyAsIterableOf(InstanceOf<T> type) {
    return fixture.createMany(type);
  }

  public static <T> Iterable<T> manyAsIterableOf(Class<T> clazz) {
    return fixture.create(new InstanceOf<Iterable<T>>() {});
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
    return (T[])fixture.createMany(TypeToken.of(clazz)).toArray();
  }

  public static <T> T[] manyAsArrayOf(InstanceOf<T> type) {
    return (T[])fixture.createMany(type).toArray();
  }

  public static <T> T[] manyAsArrayOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues)
  {
    Iterable<T> iterable = manyAsIterableOf(typeToken, omittedValues);
    List<T> list = new ArrayList<>();
    for (T element : iterable) {
      list.add(element);
    }

    return (T[]) list.toArray();
  }

  public static <T> T[] manyAsArrayOf(Class<T> type, Generate.OtherThanValues<T> omittedValues)
  {
    return manyAsArrayOf(TypeToken.of(type), omittedValues);
  }

  //LISTS

  public static <T> List<T> manyAsListOf(Class<T> clazz) {
    return Lists.newArrayList(fixture.createMany(TypeToken.of(clazz)));
  }

  public static <T> List<T> manyAsListOf(InstanceOf<T> type) {
    return Lists.newArrayList(fixture.createMany(type));
  }

  public static <T> List<T> manyAsListOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues) {
    List<T> result = new ArrayList<>();
    result.add(any(typeToken, omittedValues));
    result.add(any(typeToken, omittedValues));
    result.add(any(typeToken, omittedValues));

    return result;
  }

  public static <T> List<T> manyAsListOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  // COLLECTIONS:

  public static <T> Collection<T> manyAsCollectionOf(TypeToken<T> typeToken, Generate.OtherThanValues<T> omittedValues) {
    return manyAsListOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(Class<T> type, Generate.OtherThanValues<T> omittedValues) {
    return manyAsListOf(TypeToken.of(type), omittedValues);
  }

  public static <T> Collection<T> manyAsCollectionOf(Class<T> clazz) {
    return fixture.create(new InstanceOf<Collection<T>>() {});
  }

  public static <T> Collection<T> manyAsCollectionOf(InstanceOf<T> instanceType) {
    return fixture.createMany(instanceType);
  }

  public static <T> Set<T> manyAsSetOf(Class<T> clazz) {
    return fixture.create(new InstanceOf<Set<T>>());
  }

  public static <T> Queue<T> manyAsQueueOf(Class<T> clazz) {
    return fixture.create(new InstanceOf<Queue<T>>());
  }

  public static <T> Deque<T> manyAsDequeOf(Class<T> clazz) {
    return fixture.create(new InstanceOf<Deque<T>>());
  }

  public static <T> SortedSet<T> manyAsSortedSetOf(Class<T> clazz) {
    return fixture.create(new InstanceOf<SortedSet<T>>());
  }

  public static <T, V> SortedMap<T, V> manyAsSortedMapOf(Class<T> key, Class<V> value) {
    return fixture.create(new InstanceOf<SortedMap<T, V>>());
  }

  public static <T, V> Map<T, V> manyAsMapOf(Class<T> key, Class<V> value) {
    return fixture.create(new InstanceOf<Map<T, V>>());
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

