package autofixture.specification.acceptance;


import autofixture.publicinterface.Any;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by grzes on 15.04.2017.
 */
public class ThrowablesGenerationSpecification {


  @Test
  public void shouldGenerateRuntimeExceptions() {
    RuntimeException runtimeException = Any.runtimeException();

    assertThat(runtimeException, instanceOf(RuntimeException.class));
    assertThat(runtimeException.getMessage(), not(equalTo("")));
    assertThat(runtimeException.getMessage(), notNullValue());
  }

  @Test
  public void shouldGenerateCheckedExceptions() {
    Exception exception = Any.checkedException();

    assertThat(exception, instanceOf(Exception.class));
    assertThat(exception.getMessage(), not(equalTo("")));
    assertThat(exception.getMessage(), notNullValue());
  }

  @Test
  public void shouldGenerateThrowables() {
    Throwable throwable = Any.throwable();

    assertThat(throwable,
        allOf(
            not(instanceOf(Exception.class)),
            not(instanceOf(Error.class)),
            not(instanceOf(RuntimeException.class))));
    assertThat(throwable.getMessage(), not(equalTo("")));
    assertThat(throwable.getMessage(), notNullValue());
  }

}
