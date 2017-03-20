package autofixture.publicinterface.generators;

import autofixture.interfaces.RecursionGuard;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.FixtureContract;

public class RecursionGuarded implements GeneratorsPipeline {
  private final GeneratorsPipeline generatorsPipeline;
  private final RecursionGuard recursionGuard;

  public RecursionGuarded(final GeneratorsPipeline generatorsPipeline, final RecursionGuard recursionGuard) {
    this.generatorsPipeline = generatorsPipeline;
    this.recursionGuard = recursionGuard;
  }

  @Override
  public <T> T executeFor(final InstanceType<T> instanceType, final FixtureContract fixture) {
    try {
      recursionGuard.addDepthLevelTo(instanceType);
      return recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(generatorsPipeline, instanceType, fixture);
    } finally {
      recursionGuard.removeDepthLevelFor(instanceType);
    }

  }

  @Override
  public void registerCustomization(final InstanceGenerator instanceGenerator) {
    generatorsPipeline.registerCustomization(instanceGenerator);
  }

  @Override
  public void clearCustomizations() {
    generatorsPipeline.clearCustomizations();
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    generatorsPipeline.setOmittingAutoProperties(isOn);
  }
}
