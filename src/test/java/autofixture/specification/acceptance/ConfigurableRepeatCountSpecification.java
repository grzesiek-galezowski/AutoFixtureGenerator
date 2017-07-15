package autofixture.specification.acceptance;

import autofixture.generators.objects.implementationdetails.ConcreteInstanceType;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.matchers.HasArrayLengthMatcher;
import org.junit.Test;

import static autofixture.publicinterface.Generate.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigurableRepeatCountSpecification {
  private Fixture fixture = new Fixture();

  @Test
  public void shouldHaveDefaultRepeatCountSetTo3() {
    assertThat(new Fixture().getRepeatCount(), equalTo(3));
  }

  @Test
  public void shouldAllowRetrievingSetRepeatCount() {
    //GIVEN
    int anyRepeatCount = any(Integer.class);
    fixture.setRepeatCount(anyRepeatCount);

    //WHEN
    int retrievedRepeatCount = fixture.getRepeatCount();

    //THEN
    assertThat(retrievedRepeatCount, equalTo(anyRepeatCount));
  }

  @Test
  public void shouldGenerateCollectionsOfSizeSameAsRepeatCountFromInstanceType() {
    //GIVEN
    int anyRepeatCount = any(Integer.class);
    fixture.setRepeatCount(anyRepeatCount);

    //WHEN
    Integer[] integers = fixture.createMany(integerType()).toArray(new Integer[]{});

    //THEN
    assertThat(integers, hasItemsCount(anyRepeatCount));
  }

  @Test
  public void shouldGenerateCollectionsOfSizeSameAsRepeatCountFromInstanceOf() {
    //GIVEN
    int anyRepeatCount = any(Integer.class);
    fixture.setRepeatCount(anyRepeatCount);

    //WHEN
    Integer[] integers = fixture.createMany(new InstanceOf<Integer>() {
    }).toArray(new Integer[]{});

    //THEN
    assertThat(integers, hasItemsCount(anyRepeatCount));
  }

  private ConcreteInstanceType<Integer> integerType() {
    return new ConcreteInstanceType<>(new InstanceOf<Integer>() {
    });
  }

  private HasArrayLengthMatcher<Integer> hasItemsCount(int count) {
    return new HasArrayLengthMatcher<>(count);
  }

}
