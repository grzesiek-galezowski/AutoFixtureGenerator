package autofixture.generators.vavr;

import autofixture.generators.DefaultGeneratorsPipeline;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

public class VavrGenerator implements InstanceGenerator {
  private final GeneratorsPipeline pipeline;

  public VavrGenerator(final DefaultGeneratorsPipeline vavrPipeline) {
    pipeline = vavrPipeline;
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isFromPackage("io.vavr");
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return pipeline.executeFor(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    pipeline.setOmittingAutoProperties(isOn);
  }
}
