package autofixture.publicinterface;

import com.google.common.reflect.TypeToken;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

public class Any {

  public static String string() {
    return Generate.anyString();
  }

  public static String stringOfLength(int charactersCount) {
    return Generate.anyStringOfLength(charactersCount);
  }

  public static String stringNotContaining(String... excludedSubstrings) {
    return Generate.anyStringNotContaining(excludedSubstrings);
  }

  public static String stringContaining(String str) {
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

  public static String alphaString(int length) {
    return Generate.anyAlphaString();
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

  //bug make the argument an array instead of single value
  public static Long longOtherThan(long other) {
    return Generate.anyLongOtherThan(other);
  }

  //bug make the argument an array instead of single value
  public static String stringOtherThan(String other) {
    return Generate.anyStringOtherThan(other);
  }

  //bug make the argument an array instead of single value
  public static Integer intOtherThan(int other) {
    return Generate.anyIntegerOtherThan(other);
  }

  //bug make the argument an array instead of single value
  public static Short shortOtherThan(short other) {
    return Generate.anyShortOtherThan(other);
  }

  //bug make the argument an array instead of single value
  public static Double doubleOtherThan(double other) {
    return Generate.any(new InstanceOf<Double>() {
    }, InlineGenerators.otherThan(other));
  }

  //bug make the argument an array instead of single value
  public static Float floatOtherThan(float other) {
    return Generate.any(new InstanceOf<Float>() {
    }, InlineGenerators.otherThan(other));
  }

  public static <T> T of(Class<T> enumClass) {
    return Generate.anyOf(enumClass);
  }

  public static Date date() {
    return Generate.anyDate();
  }

  public static <T> T exploding(Class<T> clazz) {
    return Generate.anyExploding(clazz);
  }

  public static <T> T exploding(TypeToken<T> typeToken) {
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

  public static <T> T from(T... possibleValues) {
    return Generate.anyFrom(possibleValues);
  }


  // ITERABLES - complete

  public static <T> Iterable<T> iterableOf(InstanceOf<T> type) {
    return Generate.manyAsIterableOf(type);
  }

  public static <T> Iterable<T> iterableOf(Class<T> clazz) {
    return Generate.manyAsIterableOf(clazz);
  }

  public static <T> Iterable<T> iterableOf(
    TypeToken<T> typeToken, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsIterableOf(typeToken, omittedValues);
  }

  public static <T> Iterable<T> iterableOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsIterableOf(type, omittedValues);
  }

  //ARRAYS - complete
  public static <T> T[] arrayOf(Class<T> clazz) {
    return Generate.manyAsArrayOf(clazz);
  }

  public static <T> T[] arrayOf(InstanceOf<T> type) {
    return Generate.manyAsArrayOf(type);
  }

  public static <T> T[] arrayOf(TypeToken<T> typeToken, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsArrayOf(typeToken, omittedValues);
  }

  public static <T> T[] arrayOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues)
  {
    return Generate.manyAsArrayOf(type, omittedValues);
  }

  //LISTS - complete

  public static <T> List<T> listOf(Class<T> clazz) {
    return Generate.manyAsListOf(clazz);
  }

  public static <T> List<T> listOf(InstanceOf<T> type) {
    return Generate.manyAsListOf(type);
  }

  public static <T> List<T> listOf(TypeToken<T> typeToken, InlineConstrainedGenerator<T> generator) {
    return Generate.manyAsListOf(typeToken, generator);
  }

  public static <T> List<T> listOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsListOf(type, omittedValues);
  }

  // COLLECTIONS - complete

  public static <T> Collection<T> collectionOf(TypeToken<T> typeToken, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsCollectionOf(typeToken, omittedValues);
  }

  public static <T> Collection<T> collectionOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsCollectionOf(type, omittedValues);
  }

  public static <T> Collection<T> collectionOf(Class<T> clazz) {
    return Generate.manyAsCollectionOf(clazz);
  }

  public static <T> Collection<T> collectionOf(TypeToken<T> instanceType) {
    return Generate.manyAsCollectionOf(instanceType);
  }

  //SETS: incomplete

  //TODO variations
  public static <T> Set<T> setOf(Class<T> clazz) {
    return Generate.manyAsSetOf(clazz);
  }

  public static <T> Set<T> setOf(TypeToken<T> type) {
    return Generate.manyAsSetOf(type);
  }

  //TODO UT
  public static <T> Set<T> setOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSetOf(type, omittedValues);
  }
  //TODO UT
  public static <T> Set<T> setOf(TypeToken<T> type,InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSetOf(type, omittedValues);
  }


  //queues: incomplete
  public static <T> Queue<T> queueOf(Class<T> clazz) {
    return Generate.manyAsQueueOf(clazz);
  }

  public static <T> Queue<T> queueOf(TypeToken<T> type) {
    return Generate.manyAsQueueOf(type);
  }

  //TODO UT
  public static <T> Queue<T> queueOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsQueueOf(type, omittedValues);
  }
  //TODO UT
  public static <T> Queue<T> queueOf(TypeToken<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsQueueOf(type, omittedValues);
  }

  //Deques: incomplete
  public static <T> Deque<T> dequeOf(Class<T> clazz) {
    return Generate.manyAsDequeOf(clazz);
  }

  public static <T> Deque<T> dequeOf(TypeToken<T> type) {
    return Generate.manyAsDequeOf(type);
  }

  //TODO UT
  public static <T> Deque<T> dequeOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsDequeOf(type, omittedValues);
  }
  //TODO UT
  public static <T> Deque<T> dequeOf(TypeToken<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsDequeOf(type, omittedValues);
  }

  //sorted sets: incomplete
  public static <T> SortedSet<T> sortedSetOf(Class<T> clazz) {
    return Generate.manyAsSortedSetOf(clazz);
  }

  public static <T> SortedSet<T> sortedSetOf(TypeToken<T> type) {
    return Generate.manyAsSortedSetOf(type);
  }

  //TODO UT
  public static <T> SortedSet<T> sortedSetOf(Class<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSortedSetOf(type, omittedValues);
  }

  //TODO UT
  public static <T> SortedSet<T> sortedSetOf(TypeToken<T> type, InlineConstrainedGenerator<T> omittedValues) {
    return Generate.manyAsSortedSetOf(type, omittedValues);
  }

  //sorted maps

  //TODO variations and UT
  public static <T, V> SortedMap<T, V> sortedMapBetween(Class<T> key, Class<V> value) {
    return Generate.manyAsSortedMapBetween(key, value);
  }

  public static <T, V> SortedMap<T, V> sortedMapBetween(TypeToken<T> key, TypeToken<V> value) {
    return Generate.manyAsSortedMapBetween(key, value);
  }

  //maps

  //TODO variations
  public static <T, V> Map<T, V> mapBetween(Class<T> keyClass, Class<V> valueClass) {
    return Generate.manyAsMapBetween(keyClass, valueClass);
  }

  public static <T, V> Map<T, V> mapBetween(TypeToken<T> keyType, TypeToken<V> valueType) {
    return Generate.manyAsMapBetween(keyType, valueType);
  }


}
