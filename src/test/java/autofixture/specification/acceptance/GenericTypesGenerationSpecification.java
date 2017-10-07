package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import org.junit.Test;

import java.util.List;

import static autofixture.specification.acceptance.CustomAssertions.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class GenericTypesGenerationSpecification {
  @Test
  public void shouldThrowAnExceptionWhenTryingToCreateGenericInstanceThroughNonGenericMethod() {
    Fixture f = new Fixture();
    RuntimeException resultingException =
        assertThrows(RuntimeException.class, () -> f.create(List.class));
    assertThat(resultingException.getMessage(), is(equalTo("List is a generic class, " +
        "which cannot be instantiated using create(Class<T>) method. " +
        "It should be created using create(InstanceOf<T>) method " +
        "like this (example for List<T> class): " +
        "create(new InstanceOf<List<Integer>>() {}); " +
        "(note the {} brackets - they are mandatory!)")));
  }

  @Test
  public void shouldThrowAnExceptionWhenTryingToFreezeGenericTypeThroughNonGenericMethod() {
    Fixture f = new Fixture();
    RuntimeException resultingException =
        assertThrows(RuntimeException.class, () -> f.freeze(List.class));
    assertThat(resultingException.getMessage(), is(equalTo("List is a generic class, " +
        "which cannot be instantiated using freeze(Class<T>) method. " +
        "It should be created using freeze(InstanceOf<T>) method " +
        "like this (example for List<T> class): " +
        "freeze(new InstanceOf<List<Integer>>() {}); " +
        "(note the {} brackets - they are mandatory!)")));
  }

  @Test
  public void shouldThrowAnExceptionWhenTryingToCreateDummyGenericInstanceThroughNonGenericMethod() {
    Fixture f = new Fixture();
    RuntimeException resultingException =
        assertThrows(RuntimeException.class, () -> f.createDummy(List.class));
    assertThat(resultingException.getMessage(), is(equalTo("List is a generic class, " +
        "which cannot be instantiated using createDummy(Class<T>) method. " +
        "It should be created using createDummy(InstanceOf<T>) method " +
        "like this (example for List<T> class): " +
        "createDummy(new InstanceOf<List<Integer>>() {}); " +
        "(note the {} brackets - they are mandatory!)")));
  }
}
