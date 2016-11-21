package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.awt.*;
import java.awt.color.ColorSpace;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public class OtherTypesGenerationSpecification {
  @DataPoint
  public static InstanceOf<Color> color = new InstanceOf<Color>() {
  };
  @DataPoint
  public static InstanceOf<ColorSpace> optionalIntClass = new InstanceOf<ColorSpace>() {
  };
  private final Fixture fixture = new Fixture();

  @Theory
  public void shouldGenerateDifferentValuesEachTime(InstanceOf<?> instanceOfType) {
    Object o1 = fixture.create(instanceOfType);
    Object o2 = fixture.create(instanceOfType);

    assertThat(o1, not(nullValue()));
    assertThat(o2, not(nullValue()));
    assertThat(o1, not(o2));
    assertThat(o1, not(equalTo(o2)));
  }

}
