package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import com.google.common.reflect.TypeToken;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public class CustomLibraryObjectGenerationSpecification {

  @DataPoint
  public static Class<URI> uriClass = URI.class;
  @DataPoint
  public static Class<URL> urlClass = URL.class;
  @DataPoint
  public static Class<InetAddress> ipClass = InetAddress.class;
  Fixture fixture = new Fixture();

  @SafeVarargs
  static <E> E[] array(E... array) {
    return array;
  }

  @Theory
  public void shouldGenerateDifferentValuesEachTime(Class<?> clazz) {
    System.out.println(clazz);

    Object value1 = fixture.create(TypeToken.of(clazz));
    Object value2 = fixture.create(TypeToken.of(clazz));

    assertThat(value1, is(notNullValue()));
    assertThat(value2, is(notNullValue()));
    assertThat(value1, is(not(equalTo(value2))));
  }

}
