package autofixture.specification.unit;

import autofixture.interfaces.FixtureContract;
import autofixture.publicinterface.InstanceOf;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.implementationdetails.RecursionGuard;
import autofixture.publicinterface.generators.implementationdetails.ConcreteInstanceType;
import com.google.common.reflect.TypeToken;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static autofixture.publicinterface.Generate.any;
import static autofixture.publicinterface.Generate.anyString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecursionGuardSpecification {
  final InstanceType<Integer> integerInstanceType = IntegerType();
  final InstanceType<String> stringInstanceType = StringType();
  final Integer anyInteger = any(Integer.class);
  final String anyString = anyString();
  Mockery context = new Mockery();

  private static InstanceType<Integer> IntegerType() {
    return new ConcreteInstanceType<>(TypeToken.of(Integer.class));
  }

  @Test
  public void shouldReturnValueFromFixtureWhenRecursionForTypeIsNotReached() {
    //GIVEN
    final RecursionGuard recursionGuard = new RecursionGuard(3);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(FixtureContract.class);

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture);
      will(returnValue(anyInteger));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyInteger));
  }

  @Test
  public void shouldReturnValueFromFixtureWhenRecursionForTypeIsNotReachedBecauseOfRemovingDepthLevel() {
    //GIVEN
    final RecursionGuard recursionGuard = new RecursionGuard(3);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(FixtureContract.class);

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture);
      will(returnValue(anyInteger));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.removeDepthLevelFor(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyInteger));
  }

  @Test
  public void shouldReturnNullWhenRecursionForTypeIsReached() {
    //GIVEN
    final RecursionGuard recursionGuard = new RecursionGuard(3);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(new InstanceOf<FixtureContract>() {
    });

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture);
      will(returnValue(anyInteger));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(null));
  }

  @Test
  public void shouldReturnProperValueForTypeOtherThanOneThatReachedRecursionLimit() {
    //GIVEN
    final RecursionGuard recursionGuard = new RecursionGuard(3);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(new InstanceOf<FixtureContract>() {
    });

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture);
      will(returnValue(anyInteger));
      allowing(generatorPipeline).executeFor(stringInstanceType, anyFixture);
      will(returnValue(anyString));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    String value = recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(
      generatorPipeline, stringInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyString));
  }

  private InstanceType<String> StringType() {
    return new ConcreteInstanceType<>(TypeToken.of(String.class));
  }
}
