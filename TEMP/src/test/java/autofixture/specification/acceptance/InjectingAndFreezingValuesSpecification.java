package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import org.junit.Test;

import static autofixture.publicinterface.Generate.anyInteger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class InjectingAndFreezingValuesSpecification {
  Fixture fixture = new Fixture();

  @Test
  public void shouldAlwaysReturnInjectedElementWhenAskedForParticularType() {
    //GIVEN
    int anyInt = anyInteger();
    fixture.inject(new Integer(anyInt));

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
    int anyInt = anyInteger();
    fixture.inject(new Integer(anyInt));

    //WHEN
    String createdValue1 = fixture.create(String.class);
    String createdValue2 = fixture.create(String.class);

    //THEN
    assertThat(createdValue1, not(equalTo(createdValue2)));
  }

  @Test
  public void shouldOverridePreviousInjectWithNextOne() {
    //GIVEN
    int anyInt = anyInteger();
    int anyInt2 = anyInteger();
    fixture.inject(new Integer(anyInt));
    fixture.inject(new Integer(anyInt2));

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
