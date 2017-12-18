package autofixture.specification.unit;

import autofixture.generators.objects.implementationdetails.ConcreteInstanceType;
import autofixture.implementationdetails.MapBasedRecursionGuard;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.RecursionGuard;
import autofixture.publicinterface.Any;
import autofixture.publicinterface.InstanceOf;
import com.google.common.reflect.TypeToken;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecursionGuardSpecification {
  private final InstanceType<Integer> integerInstanceType = IntegerType();
  private final InstanceType<String> stringInstanceType = StringType();
  private final Integer anyInteger = Any.anonymous(Integer.class);
  private final Integer anyInteger2 = Any.anonymous(Integer.class);
  private final String anyString = Any.string();
  private final String lrMock = "LRMock";
  private final String anyString2 = Any.string();
  private Mockery context = new Mockery();

  private static InstanceType<Integer> IntegerType() {
    return new ConcreteInstanceType<>(TypeToken.of(Integer.class));
  }

  @Test
  public void shouldReturnValueFromFixtureWhenRecursionForTypeIsNotReached() {
    //GIVEN
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(3
    );
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = Any.anonymous(FixtureContract.class);

    context.checking(new Expectations() {{
      allowing(generatorPipeline).generateInstanceOf(integerInstanceType, anyFixture); will(returnValue(anyInteger));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.generateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyInteger));
  }

  @Test
  public void shouldReturnValueFromFixtureWhenRecursionForTypeIsNotReachedBecauseOfRemovingDepthLevel() {
    //GIVEN
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(3);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = Any.anonymous(FixtureContract.class);

    context.checking(new Expectations() {{
      allowing(generatorPipeline).generateInstanceOf(integerInstanceType, anyFixture); will(returnValue(anyInteger));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.removeDepthLevelFor(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.generateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyInteger));
  }

  @Test //todo not true anymore
  public void shouldReturnNullWhenRecursionForTypeIsReached() {
    //GIVEN
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(
        3);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = Any.anonymous(new InstanceOf<FixtureContract>() {
    });

    context.checking(new Expectations() {{
      allowing(generatorPipeline).generateInstanceOf(integerInstanceType, anyFixture); will(returnValue(anyInteger));
      allowing(generatorPipeline).generateEmptyInstanceOf(integerInstanceType, anyFixture); will(returnValue(anyInteger2));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.generateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyInteger2));
  }

  @Test
  public void shouldReturnProperValueForTypeOtherThanOneThatReachedRecursionLimit() {
    //GIVEN
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(3
    );
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = Any.anonymous(new InstanceOf<FixtureContract>() {
    });

    context.checking(new Expectations() {{
      allowing(generatorPipeline).generateEmptyInstanceOf(stringInstanceType, anyFixture); will(returnValue(anyString2));
      allowing(generatorPipeline).generateInstanceOf(stringInstanceType, anyFixture); will(returnValue(anyString));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    String value = recursionGuard.generateUsing(
      generatorPipeline, stringInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyString));
  }

  private InstanceType<String> StringType() {
    return new ConcreteInstanceType<>(TypeToken.of(String.class));
  }
}
