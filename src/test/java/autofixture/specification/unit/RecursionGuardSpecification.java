package autofixture.specification.unit;

import autofixture.implementationdetails.MapBasedRecursionGuard;
import autofixture.interfaces.*;
import autofixture.publicinterface.InstanceOf;
import autofixture.implementationdetails.ConcreteInstanceType;
import autofixture.publicinterface.generators.DefaultGeneratorsPipeline;
import com.google.common.reflect.TypeToken;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import java.util.ArrayList;

import static autofixture.publicinterface.Generate.any;
import static autofixture.publicinterface.Generate.anyString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecursionGuardSpecification {
  private final InstanceType<Integer> integerInstanceType = IntegerType();
  private final InstanceType<String> stringInstanceType = StringType();
  private final Integer anyInteger = any(Integer.class);
  private final Integer anyInteger2 = any(Integer.class);
  private final String anyString = anyString();
  private final String lrMock = "LRMock";
  private Mockery context = new Mockery();

  private static InstanceType<Integer> IntegerType() {
    return new ConcreteInstanceType<>(TypeToken.of(Integer.class));
  }

  @Test
  public void shouldReturnValueFromFixtureWhenRecursionForTypeIsNotReached() {
    //GIVEN
    final GeneratorsPipeline limitReachedPipeline = context.mock(GeneratorsPipeline.class, lrMock);
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(3,
        limitReachedPipeline);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(FixtureContract.class);

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger));
      allowing(limitReachedPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger2));
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
    final GeneratorsPipeline limitReachedPipeline = context.mock(GeneratorsPipeline.class, lrMock);
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(3, limitReachedPipeline);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(FixtureContract.class);

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger));
      allowing(limitReachedPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger2));
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
    final GeneratorsPipeline limitReachedPipeline = context.mock(GeneratorsPipeline.class, lrMock);
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(
        3, limitReachedPipeline);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(new InstanceOf<FixtureContract>() {
    });

    context.checking(new Expectations() {{
      allowing(limitReachedPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger2));
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger));
    }});

    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);
    recursionGuard.addDepthLevelTo(integerInstanceType);

    //WHEN
    Integer value = recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(
      generatorPipeline, integerInstanceType, anyFixture);

    //THEN
    assertThat(value, equalTo(anyInteger2));
  }

  @Test
  public void shouldReturnProperValueForTypeOtherThanOneThatReachedRecursionLimit() {
    //GIVEN
    final GeneratorsPipeline limitReachedPipeline = context.mock(GeneratorsPipeline.class, lrMock);
    final RecursionGuard recursionGuard = new MapBasedRecursionGuard(3,
        limitReachedPipeline);
    final GeneratorsPipeline generatorPipeline = context.mock(GeneratorsPipeline.class);
    final FixtureContract anyFixture = any(new InstanceOf<FixtureContract>() { });

    context.checking(new Expectations() {{
      allowing(generatorPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger));
      allowing(generatorPipeline).executeFor(stringInstanceType, anyFixture); will(returnValue(anyString));
      allowing(limitReachedPipeline).executeFor(integerInstanceType, anyFixture); will(returnValue(anyInteger2));
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
