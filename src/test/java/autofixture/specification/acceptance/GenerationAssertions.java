package autofixture.specification.acceptance;

import autofixture.specification.acceptance.testfixtures.GenericInterface;
import autofixture.specification.acceptance.testfixtures.GenericObject2;

import java.util.Collection;

import static autofixture.specification.acceptance.TypeHelpers.intValue;
import static autofixture.specification.acceptance.TypeHelpers.string;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class GenerationAssertions {
  public static void assertGenericObject2GeneratedCorrectly(GenericInterface<GenericObject2<Integer>> o) {
    assertThat(o.getInstance(), instanceOf(GenericObject2.class));
    assertThat(o.getInstance().field, instanceOf(intValue()));
  }

  public static void assertContainsOnlyStrings(Collection<String> collection) {
    assertThat(collection.toArray()[0], instanceOf(string()));
    assertThat(collection.toArray()[1], instanceOf(string()));
    assertThat(collection.toArray()[2], instanceOf(string()));
  }

  public static void assertContainsOnlyIntegers(Collection<Integer> collection) {
    assertContainsOnly(collection, intValue());
  }

  public static <T> void assertContainsOnly(final Collection<T> collection, final Class<T> type) {
    assertThat(collection.toArray()[0], instanceOf(type));
    assertThat(collection.toArray()[1], instanceOf(type));
    assertThat(collection.toArray()[2], instanceOf(type));
  }
}
