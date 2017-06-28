package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.specification.acceptance.testfixtures.RecursiveStructure;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class RecursionGuardingSpecification {
  private Fixture fixture = new Fixture();

  @Test
  public void shouldHandleRecursiveClasses() {
    RecursiveStructure instance = fixture.create(RecursiveStructure.class);
    assertThat(instance, is(notNullValue()));
  }

  @Test
  public void shouldReturnFakeAsRecursiveObjectWhenItsRecursionIsFinished() {
    fixture.setRecursionDepth(1);
    RecursiveStructure instance = fixture.create(RecursiveStructure.class);

    assertThat(instance.inner.inner, equalTo(null));
  }

}
