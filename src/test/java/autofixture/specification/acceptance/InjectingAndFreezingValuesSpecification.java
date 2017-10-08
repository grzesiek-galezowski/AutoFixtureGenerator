package autofixture.specification.acceptance;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class InjectingAndFreezingValuesSpecification {
  Fixture fixture = new Fixture();

  @Test
  public void shouldAlwaysReturnInjectedElementWhenAskedForParticularType() {
    //GIVEN
    int anyInt = Any.intValue();
    fixture.inject(Integer.valueOf(anyInt));

    //WHEN
    int createdValue1 = fixture.create(Integer.class);
    int createdValue2 = fixture.create(Integer.class);

    //THEN
    assertThat(createdValue1, equalTo(anyInt));
    assertThat(createdValue2, equalTo(anyInt));
  }

  @Test
  public void shouldNotImpactOtherTypesGenerationWithInjection() {
    //GIVEN
    int anyInt = Any.intValue();
    fixture.inject(Integer.valueOf(anyInt));

    //WHEN
    String createdValue1 = fixture.create(String.class);
    String createdValue2 = fixture.create(String.class);

    //THEN
    assertThat(createdValue1, not(equalTo(createdValue2)));
  }

  @Test
  public void shouldOverridePreviousInjectWithNextOne() {
    //GIVEN
    int anyInt = Any.intValue();
    int anyInt2 = Any.intValue();
    fixture.inject(Integer.valueOf(anyInt));
    fixture.inject(Integer.valueOf(anyInt2));

    //WHEN
    int createdValue1 = fixture.create(Integer.class);
    int createdValue2 = fixture.create(Integer.class);

    //THEN
    assertThat(createdValue1, equalTo(anyInt2));
    assertThat(createdValue2, equalTo(anyInt2));
  }

  @Test
  public void shouldBeAbleToFreezeValueOnDemand() {
    int frozenInteger1 = fixture.freeze(new InstanceOf<Integer>() {
    });
    int frozenInteger2 = fixture.freeze(int.class);
    int frozenInteger3 = fixture.create(int.class);

    assertThat(frozenInteger1, equalTo(frozenInteger2));
    assertThat(frozenInteger1, equalTo(frozenInteger3));
  }

}
