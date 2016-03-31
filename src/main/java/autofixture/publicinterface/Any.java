package autofixture.publicinterface;

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
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public class Any {

  public static <T> T anonymous(final TypeToken<T> instanceType) {
    return Generate.any(instanceType);
  }

  public static <T> T anonymous(final Class<T> clazz) {
    return Generate.any(clazz);
  }

  public static <T> T anonymous(final InlineInstanceGenerator<T> generator) {
    return Generate.any(generator);
  }

  public static <T> T anonymous(final TypeToken<T> type, final InlineConstrainedGenerator<T> generator) {
    return Generate.any(type, generator);
  }

  public static <T> T anonymous(final Class<T> instanceType, final InlineConstrainedGenerator<T> generator) {
    return Generate.any(instanceType, generator);
  }

  public static String string() {
    return Generate.anyString();
  }

  public static String stringOfLength(final int charactersCount) {
    return Generate.anyStringOfLength(charactersCount);
  }

  public static String stringNotContaining(final String... excludedSubstrings) {
    return Generate.anyStringNotContaining(excludedSubstrings);
  }

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

  public static Long longOtherThan(final long... other) {
    return Generate.anyLongOtherThan(other);
  }

  public static String stringOtherThan(final String... other) {
    return Generate.anyStringOtherThan(other);
  }

  public static Integer intOtherThan(final int... other) {
    return Generate.anyIntegerOtherThan(other);
  }

  public static Short shortOtherThan(final short... other) {
    return Generate.anyShortOtherThan(other);
  }

  public static Double doubleOtherThan(final double... other) {
    return Generate.anyDoubleOtherThan(other);
  }

  public static Float floatOtherThan(final float... other) {
    return Generate.anyFloatOtherThan(other);
  }

  public static <T> T of(final Class<T> enumClass) {
    return Generate.anyOf(enumClass);
  }

  public static Date date() {
    return Generate.anyDate();
  }

  public static <T> T exploding(final Class<T> clazz) {
    return Generate.anyExploding(clazz);
  }

  public static <T> T exploding(final TypeToken<T> typeToken) {
    return Generate.anyExploding(typeToken);
  }

  public static Exception exception() {
    return Generate.anyException();
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

  public static <T> T from(final T... possibleValues) {
    return Generate.anyFrom(possibleValues);
  }


  // ITERABLES - complete

  public static <T> Iterable<T> iterableOf(final InstanceOf<T> type) {
    return Generate.manyAsIterableOf(type);
  }

  public static <T> Iterable<T> iterableOf(final Class<T> clazz) {
    return Generate.manyAsIterableOf(clazz);
  }

  public static <T> Iterable<T> iterableOf(
      final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsIterableOf(typeToken, omittedValues);
  }

  public static <T> Iterable<T> iterableOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsIterableOf(type, omittedValues);
  }

  //ARRAYS - complete
  public static <T> T[] arrayOf(final Class<T> clazz) {
    return Generate.manyAsArrayOf(clazz);
  }

  public static <T> T[] arrayOf(final InstanceOf<T> type) {
    return Generate.manyAsArrayOf(type);
  }

  public static <T> T[] arrayOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsArrayOf(typeToken, omittedValues);
  }

  public static <T> T[] arrayOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    T[] array = Generate.manyAsArrayOf(type, omittedValues);
    return array;
  }

  //LISTS - complete

  public static <T> List<T> listOf(final Class<T> clazz) {
    return Generate.manyAsListOf(clazz);
  }

  public static <T> List<T> listOf(final InstanceOf<T> type) {
    return Generate.manyAsListOf(type);
  }

  public static <T> List<T> listOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> generator) {
    return Generate.manyAsListOf(typeToken, generator);
  }

  public static <T> List<T> listOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsListOf(type, omittedValues);
  }

  // COLLECTIONS - complete

  public static <T> Collection<T> collectionOf(final TypeToken<T> typeToken, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsCollectionOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> collectionOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsCollectionOf(type, omittedValues);
  }

  public static <T> Collection<T> collectionOf(final Class<T> clazz) {
    return Generate.manyAsCollectionOf(clazz);
  }

  public static <T> Collection<T> collectionOf(final TypeToken<T> instanceType) {
    return Generate.manyAsCollectionOf(instanceType);
  }

  //SETS: incomplete

  //TODO variations
  public static <T> Set<T> setOf(final Class<T> clazz) {
    return Generate.manyAsSetOf(clazz);
  }

  public static <T> Set<T> setOf(final TypeToken<T> type) {
    return Generate.manyAsSetOf(type);
  }

  //TODO UT
  public static <T> Set<T> setOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSetOf(type, omittedValues);
  }

  //TODO UT
  public static <T> Set<T> setOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSetOf(type, omittedValues);
  }


  //queues: incomplete
  public static <T> Queue<T> queueOf(final Class<T> clazz) {
    return Generate.manyAsQueueOf(clazz);
  }

  public static <T> Queue<T> queueOf(final TypeToken<T> type) {
    return Generate.manyAsQueueOf(type);
  }

  //TODO UT
  public static <T> Queue<T> queueOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsQueueOf(type, omittedValues);
  }

  //TODO UT
  public static <T> Queue<T> queueOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsQueueOf(type, omittedValues);
  }

  //Deques: incomplete
  public static <T> Deque<T> dequeOf(final Class<T> clazz) {
    return Generate.manyAsDequeOf(clazz);
  }

  public static <T> Deque<T> dequeOf(final TypeToken<T> type) {
    return Generate.manyAsDequeOf(type);
  }

  //TODO UT
  public static <T> Deque<T> dequeOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsDequeOf(type, omittedValues);
  }

  //TODO UT
  public static <T> Deque<T> dequeOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsDequeOf(type, omittedValues);
  }

  //sorted sets: incomplete
  public static <T> SortedSet<T> sortedSetOf(final Class<T> clazz) {
    return Generate.manyAsSortedSetOf(clazz);
  }

  public static <T> SortedSet<T> sortedSetOf(final TypeToken<T> type) {
    return Generate.manyAsSortedSetOf(type);
  }

  //TODO UT
  public static <T> SortedSet<T> sortedSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSortedSetOf(type, omittedValues);
  }

  //TODO UT
  public static <T> SortedSet<T> sortedSetOf(final TypeToken<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSortedSetOf(type, omittedValues);
  }

  //sorted maps

  //TODO variations and UT
  public static <T, V> SortedMap<T, V> sortedMapBetween(final Class<T> key, final Class<V> value) {
    return Generate.manyAsSortedMapBetween(key, value);
  }

  public static <T, V> SortedMap<T, V> sortedMapBetween(final TypeToken<T> key, final TypeToken<V> value) {
    return Generate.manyAsSortedMapBetween(key, value);
  }

  //maps

  //TODO variations
  public static <T, V> Map<T, V> mapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    return Generate.manyAsMapBetween(keyClass, valueClass);
  }

  public static <T, V> Map<T, V> mapBetween(final TypeToken<T> keyType, final TypeToken<V> valueType) {
    return Generate.manyAsMapBetween(keyType, valueType);
  }


}
