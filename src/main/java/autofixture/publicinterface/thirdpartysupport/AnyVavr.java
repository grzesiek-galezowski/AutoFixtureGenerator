package autofixture.publicinterface.thirdpartysupport;

import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.publicinterface.Any;
import autofixture.publicinterface.InstanceOf;
import io.vavr.collection.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.NonNull;

import static autofixture.generators.objects.implementationdetails.TypeAssertions.assertIsNotParameterized;
import static autofixture.implementationdetails.ErrorMessages.msg;
import static autofixture.implementationdetails.ErrorMessages.msgInline;

public class AnyVavr {
  //todo add generics checks
  @NonNull
  public static <T> List<T> listOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("listOf"));
    return io.vavr.collection.List.ofAll(Any.listOf(clazz));
  }


  @NonNull
  public static <T> List<T> listOf(final InstanceOf<T> clazz) {
    return io.vavr.collection.List.ofAll(Any.listOf(clazz));

  }

  @NonNull
  public static <T> List<T> listOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return io.vavr.collection.List.ofAll(Any.listOf(clazz, generator));
  }

  @NonNull
  public static <T> List<T> listOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("listOf"));
    return List.ofAll(Any.listOf(type, omittedValues));
  }

/////////////// arrays

  @NonNull
  public static <T> Array<T> arrayOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("arrayOf"));
    return Array.ofAll(Any.listOf(clazz));
  }

  @NonNull
  public static <T> Array<T> arrayOf(final InstanceOf<T> clazz) {
    return Array.ofAll(Any.listOf(clazz));
  }

  @NonNull
  public static <T> Array<T> arrayOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return Array.ofAll(Any.listOf(clazz, generator));
  }

  @NonNull
  public static <T> Array<T> arrayOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("arrayOf"));
    return Array.ofAll(Any.listOf(type, omittedValues));
  }

  /////////////// hashsets

  @NonNull
  public static <T> HashSet<T> hashSetOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("hashSetOf"));
    return HashSet.ofAll(Any.listOf(clazz));
  }

  @NonNull
  public static <T> HashSet<T> hashSetOf(final InstanceOf<T> clazz) {
    return HashSet.ofAll(Any.listOf(clazz));
  }

  @NonNull
  public static <T> HashSet<T> hashSetOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return HashSet.ofAll(Any.listOf(clazz, generator));
  }

  @NonNull
  public static <T> HashSet<T> hashSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("hashSetOf"));
    return HashSet.ofAll(Any.listOf(type, omittedValues));
  }

  //////// tree sets

  @NonNull
  public static <T> TreeSet<T> treeSetOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("treeSetOf"));
    return TreeSet.ofAll((Iterable)Any.iterableOf(clazz));
  }

  @NonNull
  public static <T> TreeSet<T> treeSetOf(final InstanceOf<T> clazz) {
    return TreeSet.ofAll((Iterable)Any.listOf(clazz));
  }

  @NonNull
  public static <T> TreeSet<T> treeSetOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return TreeSet.ofAll((Iterable)Any.listOf(clazz, generator));
  }

  @NonNull
  public static <T> TreeSet<T> treeSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("treeSetOf"));
    return TreeSet.ofAll((Iterable)Any.listOf(type, omittedValues));
  }

//////// sets


  @NonNull
  public static <T> Set<T> setOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("setOf"));
    return hashSetOf(clazz);
  }

  @NonNull
  public static <T> Set<T> setOf(final InstanceOf<T> clazz) {
    return hashSetOf(clazz);
  }

  @NonNull
  public static <T> Set<T> setOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return hashSetOf(clazz, generator);
  }

  @NonNull
  public static <T> Set<T> setOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("setOf"));
    return hashSetOf(type, omittedValues);
  }

/////// sorted sets

  @NonNull
  public static <T> SortedSet<T> sortedSetOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("sortedSetOf"));
    return treeSetOf(clazz);
  }

  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final InstanceOf<T> clazz) {
    return treeSetOf(clazz);
  }

  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return treeSetOf(clazz, generator);
  }

  @NonNull
  public static <T> SortedSet<T> sortedSetOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("sortedSetOf"));
    return treeSetOf(type, omittedValues);
  }

  /////////////////////////////// hash maps

  @NonNull
  public static <T, V> HashMap<T, V> hashMapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    assertIsNotParameterized(keyClass, "generic key types are not allowed for this method.");
    assertIsNotParameterized(valueClass, "generic value types are not allowed for this method.");
    return HashMap.ofAll(Any.mapBetween(keyClass, valueClass));
  }

  @NonNull
  public static <T, V> HashMap<T, V> hashMapBetween(final InstanceOf<T> keyType, final InstanceOf<V> valueType) {
    return HashMap.ofAll(Any.mapBetween(keyType, valueType));
  }

  /////////////////////////////// maps

  @NonNull
  public static <T, V> Map<T, V> mapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    assertIsNotParameterized(keyClass, "generic key types are not allowed for this method.");
    assertIsNotParameterized(valueClass, "generic value types are not allowed for this method.");
    return hashMapBetween(keyClass, valueClass);
  }

  @NonNull
  public static <T, V> Map<T, V> mapBetween(final InstanceOf<T> keyType, final InstanceOf<V> valueType) {
    return hashMapBetween(keyType, valueType);
  }

  @NonNull
  public static <T, V> LinkedHashMap<T, V> linkedHashMapBetween(final Class<T> keyClass, final Class<V> valueClass) {
    assertIsNotParameterized(keyClass, "generic key types are not allowed for this method.");
    assertIsNotParameterized(valueClass, "generic value types are not allowed for this method.");
    return LinkedHashMap.ofAll(Any.mapBetween(keyClass, valueClass));
  }

  @NonNull
  public static <T, V> LinkedHashMap<T, V> linkedHashMapBetween(final InstanceOf<T> keyType, final InstanceOf<V> valueType) {
    return LinkedHashMap.ofAll(Any.mapBetween(keyType, valueType));
  }

//////////////////// queues


  @NonNull
  public static <T> Queue<T> queueOf(Class<T> clazz) {
    assertIsNotParameterized(clazz, msg("queueOf"));
    return Queue.ofAll(Any.queueOf(clazz));
  }

  @NonNull
  public static <T> Queue<T> queueOf(final InstanceOf<T> clazz) {
    return Queue.ofAll(Any.queueOf(clazz));
  }

  @NonNull
  public static <T> Queue<T> queueOf(final InstanceOf<T> clazz, final InlineConstrainedGenerator<T> generator) {
    return Queue.ofAll(Any.queueOf(clazz, generator));
  }

  @NonNull
  public static <T> Queue<T> queueOf(final Class<T> type, final InlineConstrainedGenerator<T> omittedValues) {
    assertIsNotParameterized(type, msgInline("queueOf"));
    return Queue.ofAll(Any.queueOf(type, omittedValues));
  }

  // options

  @NonNull
  public static <T> Option<T> option(final Class<T> type) {
    assertIsNotParameterized(type, msgInline("option"));
    return Option.of(Any.instanceOf(type));
  }

  @NonNull
  public static <T> Option<T> option(final InstanceOf<T> type) {
    return Option.of(Any.anonymous(type));
  }

  // eithers - left

  @NonNull
  public static <T,U> Either<T, U> left(final Class<T> leftType) {
    assertIsNotParameterized(leftType, msgInline("left"));
    return Either.left(Any.instanceOf(leftType));
  }

  @NonNull
  public static <T,U> Either<T, U> left(final InstanceOf<T> leftType) {
    return Either.left(Any.anonymous(leftType));
  }

  // eithers - right

  @NonNull
  public static <T,U> Either<T, U> right(final Class<U> rightType) {
    assertIsNotParameterized(rightType, msgInline("right"));
    return Either.right(Any.instanceOf(rightType));
  }

  @NonNull
  public static <T,U> Either<T, U> right(final InstanceOf<U> rightType) {
    return Either.right(Any.anonymous(rightType));
  }

  // validations - failures

  @NonNull
  public static <T,U> Validation<T, U> validationFailed(final Class<T> type) {
    assertIsNotParameterized(type, msgInline("validationFailed"));
    return Validation.invalid(Any.instanceOf(type));
  }

  @NonNull
  public static <T,U> Validation<T, U> validationFailed(final InstanceOf<T> type) {
    return Validation.invalid(Any.anonymous(type));
  }

  // validations - successful

  @NonNull
  public static <T,U> Validation<T, U> validationSuccess(final Class<U> type) {
    assertIsNotParameterized(type, msgInline("validationSuccess"));
    return Validation.valid(Any.instanceOf(type));
  }

  @NonNull
  public static <T,U> Validation<T, U> validationSuccess(final InstanceOf<U> type) {
    return Validation.valid(Any.anonymous(type));
  }

  // try - failures

  @NonNull
  public static Try tryFailed() {
    return Try.failure(Any.throwable());
  }

  // try - successful

  @NonNull
  public static <T> Try<T> trySuccess(final Class<T> type) {
    assertIsNotParameterized(type, msgInline("trySuccess"));
    return Try.success(Any.instanceOf(type));
  }

  @NonNull
  public static <T> Try<T> trySuccess(final InstanceOf<T> type) {
    return Try.success(Any.anonymous(type));
  }

  //multimap, hashmultimap, linkedhashmultimap
  //priorityqueue,
  //charseq, indexedseq, seq

}
