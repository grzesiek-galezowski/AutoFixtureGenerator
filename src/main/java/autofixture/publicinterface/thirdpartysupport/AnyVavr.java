package autofixture.publicinterface.thirdpartysupport;

import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.publicinterface.Any;
import autofixture.publicinterface.InstanceOf;
import io.vavr.collection.List;
import lombok.NonNull;

import static autofixture.generators.objects.implementationdetails.TypeAssertions.assertIsNotParameterized;
import static autofixture.implementationdetails.ErrorMessages.msg;
import static autofixture.implementationdetails.ErrorMessages.msgInline;

public class AnyVavr {
  //todo add generics checks
  //todo add first-party check to autofixture engine
  @NonNull
  public static <T> io.vavr.collection.List<T> listOf(Class<T> clazz) {
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
}
