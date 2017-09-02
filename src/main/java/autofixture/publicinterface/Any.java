package autofixture.publicinterface;

import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.interfaces.InlineInstanceGenerator;
import lombok.NonNull;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

import static com.google.common.base.CharMatcher.is;

public class Any {

  @NonNull
  public static <T> T anonymous(final InstanceOf<T> instanceType) {
    return Generate.any(instanceType);
  }

  @NonNull
  public static <T> T anonymous(final Class<T> clazz) {
    return Generate.any(clazz);
  }

  @NonNull
  public static <T> T instanceOf(final Class<T> clazz) {
    return Generate.any(clazz);
  }

  @NonNull
  public static <T> T anonymous(final InlineInstanceGenerator<T> generator) {
    return Generate.any(generator);
  }

  @NonNull
  public static <T> T anonymous(final InstanceOf<T> type, final InlineConstrainedGenerator<T> generator) {
    return Generate.any(type, generator);
  }

  @NonNull
  public static <T> T anonymous(final Class<T> instanceType, final InlineConstrainedGenerator<T> generator) {
    return Generate.any(instanceType, generator);
  }

  @NonNull
  public static <T> T dummy(final InstanceOf<T> instanceType) {
    return Generate.dummy(instanceType);
  }

  @NonNull
  public static <T> T dummy(final Class<T> clazz) {
    return Generate.dummy(clazz);
  }

  public static String string() {
    return Generate.anyString();
  }

  @NonNull
  public static String string(String seed) {
    return Generate.anyString(seed);
  }

  public static String string(final int charactersCount) {
    return stringOfLength(charactersCount);
  }

  public static String stringOfLength(final int charactersCount) {
    return Generate.anyStringOfLength(charactersCount);
  }

  @NonNull
  public static String stringNotContaining(final String... excludedSubstrings) {
    return Generate.anyStringNotContaining(excludedSubstrings);
  }

  @NonNull
  public static String stringContaining(final String str) {
    return Generate.anyStringContaining(str);
  }

  public static Character alphaChar() {
    return Generate.anyAlphaChar();
  }

  public static Character digitChar() {
    return Generate.anyDigitChar();
  }

  public static String alphaString() {
    return Generate.anyAlphaString();
  }

  public static String alphaString(final int length) {
    return Generate.anyAlphaString(length);
  }

  public static String lowercaseString() {
    return Generate.anyLowercaseString();
  }

  public static String lowercaseString(final int length) {
    return Generate.anyLowercaseString(length);
  }


  public static String uppercaseString() {
    return Generate.anyUppercaseString();
  }
  public static String uppercaseString(final int length) {
    return Generate.anyUppercaseString(length);
  }


  public static String identifier() {
    return Generate.anyIdentifier();
  }

  public static String legalXmlTagName() {
    return Generate.anyLegalXmlTagName();
  }

  public static Integer intValue() {
    return Generate.anyInteger();
  }

  public static Short shortValue() {
    return Generate.anyShort();
  }

  public static Double doubleValue() {
    return Generate.anyDouble();
  }

  public static Float floatValue() {
    return Generate.anyFloat();
  }

  public static Character charValue() {
    return Generate.anyChar();
  }

  public static Long longValue() {
    return Generate.anyLong();
  }

  @NonNull
  public static <T> T otherThan(final T... others) {
    //todo doesn't work for generics
    return Any.anonymous((Class<T>) others[0].getClass(), InlineGenerators.otherThan(others));
  }

  @NonNull
  public static Long longOtherThan(final long... other) {
    return Generate.anyLongOtherThan(other);
  }

  @NonNull
  public static String stringOtherThan(final String... other) {
    return Generate.anyStringOtherThan(other);
  }

  @NonNull
  public static Integer intOtherThan(final int... other) {
    return Generate.anyIntegerOtherThan(other);
  }

  @NonNull
  public static Short shortOtherThan(final short... other) {
    return Generate.anyShortOtherThan(other);
  }

  @NonNull
  public static Double doubleOtherThan(final double... other) {
    return Generate.anyDoubleOtherThan(other);
  }

  @NonNull
  public static Float floatOtherThan(final float... other) {
    return Generate.anyFloatOtherThan(other);
  }

  @NonNull
  public static <T> T of(final Class<T> enumClass) {
    return Generate.anyOf(enumClass);
  }

  public static Date date() {
    return Generate.anyDate();
  }

  @NonNull
  public static <T> T exploding(final Class<T> clazz) {
    return Generate.anyExploding(clazz);
  }

  @NonNull
  public static <T> T exploding(final InstanceOf<T> typeToken) {
    return Generate.anyExploding(typeToken);
  }

  public static Exception exception() {
    return Generate.anyException();
  }

  public static RuntimeException runtimeException() {
    return Generate.anyRuntimeException();
  }

  public static Exception checkedException() {
    return Generate.anyCheckedException();
  }

  public static Throwable throwable() {
    return Generate.anyThrowable();
  }

  public static Throwable uncheckedThrowable() {
    return runtimeException();
  }


  public static Error error() {
    return Generate.anyError();
  }

  public static Boolean booleanValue() {
    return Generate.anyBoolean();
  }

  public static Object object() {
    return Generate.any(Object.class);
  }

  public static URI uri() {
    return Generate.anyUri();
  }

  public static URL url() {
    return Generate.anyUrl();
  }

  public static int port() {
    return Generate.anyPort();
  }

  public static InetAddress ip() {
    return Generate.anyIp();
  }

  public static ChronoLocalDate chronoLocalDate() {
    return Generate.anyChronoLocalDate();
  }

  public static ChronoLocalDateTime chronoLocalDateTime() {
    return Generate.anyChronoLocalDateTime();
  }

  public static LocalDateTime localDateTime() {
    return Generate.anyLocalDateTime();
  }

  public static LocalDate localDate() {
    return Generate.anyLocalDate();
  }

  public static ZonedDateTime zonedDateTime() {
    return Generate.anyZonedDateTime();
  }

  public static ZoneId zoneId() {
    return Generate.anyZoneId();
  }

  public static OffsetTime offsetTime() {
    return Generate.anyOffsetTime();
  }

  public static Period period() {
    return Generate.anyPeriod();
  }

  public static Duration duration() {
    return Generate.anyDuration();
  }

  public static ZoneOffset zoneOffset() {
    return Generate.anyZoneOffset();
  }

  public static Clock clock() {
    return Generate.anyClock();
  }

  public static Instant instant() {
    return Generate.anyInstant();
  }

  @NonNull
  public static <T> T from(final T... possibleValues) {
    return Generate.anyFrom(possibleValues);
  }


  // ITERABLES - complete
  @NonNull
  public static <T> Iterable<T> iterableOf(final InstanceOf<T> type) {
    return Generate.manyAsIterableOf(type);
  }

  @NonNull
  public static <T> Iterable<T> iterableOf(final Class<T> clazz) {
    return Generate.manyAsIterableOf(clazz);
  }

  @NonNull
  public static <T> Iterable<T> iterableOf(
      final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsIterableOf(typeToken, omittedValues);
  }

  @NonNull
  public static <T> Iterable<T> iterableOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsIterableOf(type, omittedValues);
  }

  //ARRAYS - complete
  @NonNull
  public static <T> T[] arrayOf(final Class<T> clazz) {
    return Generate.manyAsArrayOf(clazz);
  }

  @NonNull
  public static <T> T[] arrayOf(final InstanceOf<T> type) {
    return Generate.manyAsArrayOf(type);
  }

  @NonNull
  public static <T> T[] arrayOf(final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsArrayOf(typeToken, omittedValues);
  }

  @NonNull
  public static <T> T[] arrayOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    T[] array = Generate.manyAsArrayOf(type, omittedValues);
    return array;
  }

  //LISTS - complete

  @NonNull
  public static <T> List<T> listOf(final Class<T> clazz) {
    return Generate.manyAsListOf(clazz);
  }

  @NonNull
  public static <T> List<T> listOf(final InstanceOf<T> type) {
    return Generate.manyAsListOf(type);
  }

  @NonNull
  public static <T> List<T> listOf(final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> generator) {
    return Generate.manyAsListOf(typeToken, generator);
  }

  @NonNull
  public static <T> List<T> listOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsListOf(type, omittedValues);
  }

  // COLLECTIONS - complete

  @NonNull
  public static <T> Collection<T> collectionOf(final InstanceOf<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsCollectionOf(typeToken, omittedValues);
  }

  @NonNull
  public static <T> Collection<T> collectionOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsCollectionOf(type, omittedValues);
  }

  @NonNull
  public static <T> Collection<T> collectionOf(final Class<T> clazz) {
    return Generate.manyAsCollectionOf(clazz);
  }

  @NonNull
  public static <T> Collection<T> collectionOf(final InstanceOf<T> instanceType) {
    return Generate.manyAsCollectionOf(instanceType);
  }

  //SETS: incomplete

  //TODO variations
  @NonNull
  public static <T> Set<T> setOf(final Class<T> clazz) {
    return Generate.manyAsSetOf(clazz);
  }

  @NonNull
  public static <T> Set<T> setOf(final InstanceOf<T> type) {
    return Generate.manyAsSetOf(type);
  }

  //TODO UT
  @NonNull
  public static <T> Set<T> setOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSetOf(type, omittedValues);
  }

  //TODO UT
  @NonNull
  public static <T> Set<T> setOf(final InstanceOf<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSetOf(type, omittedValues);
  }


  //queues: incomplete
  @NonNull
  public static <T> Queue<T> queueOf(final Class<T> clazz) {
    return Generate.manyAsQueueOf(clazz);
  }

  @NonNull
  public static <T> Queue<T> queueOf(final InstanceOf<T> type) {
    return Generate.manyAsQueueOf(type);
  }

  //TODO UT
  @NonNull
  public static <T> Queue<T> queueOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsQueueOf(type, omittedValues);
  }

  //TODO UT
  @NonNull
  public static <T> Queue<T> queueOf(final InstanceOf<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsQueueOf(type, omittedValues);
  }

  //Deques: incomplete
  @NonNull
  public static <T> Deque<T> dequeOf(final Class<T> clazz) {
    return Generate.manyAsDequeOf(clazz);
  }

  @NonNull
  public static <T> Deque<T> dequeOf(final InstanceOf<T> type) {
    return Generate.manyAsDequeOf(type);
  }

  //TODO UT
  @NonNull
  public static <T> Deque<T> dequeOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsDequeOf(type, omittedValues);
  }

  //TODO UT
  @NonNull
  public static <T> Deque<T> dequeOf(final InstanceOf<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsDequeOf(type, omittedValues);
  }

  //sorted sets: incomplete
  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final Class<T> clazz) {
    return Generate.manyAsSortedSetOf(clazz);
  }

  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final InstanceOf<T> type) {
    return Generate.manyAsSortedSetOf(type);
  }

  //TODO UT
  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSortedSetOf(type, omittedValues);
  }

  //TODO UT
  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final InstanceOf<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSortedSetOf(type, omittedValues);
  }

  //sorted maps

  //TODO variations and UT
  @NonNull
  public static <T, V> SortedMap<T, V> sortedMapBetween(final Class<T> key, final Class<V> value) {
    return Generate.manyAsSortedMapBetween(key, value);
  }

  @NonNull
  public static <T, V> SortedMap<T, V> sortedMapBetween(final InstanceOf<T> key, final InstanceOf<V> value) {
    return Generate.manyAsSortedMapBetween(key, value);
  }

  //maps

  //TODO variations
  @NonNull
  public static <T, V> Map<T, V> mapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    return Generate.manyAsMapBetween(keyClass, valueClass);
  }

  @NonNull
  public static <T, V> Map<T, V> mapBetween(final InstanceOf<T> keyType, final InstanceOf<V> valueType) {
    return Generate.manyAsMapBetween(keyType, valueType);
  }


}
