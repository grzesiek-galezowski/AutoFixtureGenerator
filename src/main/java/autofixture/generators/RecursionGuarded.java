package autofixture.generators;

import autofixture.interfaces.*;

public class RecursionGuarded implements GeneratorsPipeline {
  private final GeneratorsPipeline generatorsPipeline;
  private final RecursionGuard recursionGuard;

  public RecursionGuarded(final GeneratorsPipeline generatorsPipeline, final RecursionGuard recursionGuard) {
    this.generatorsPipeline = generatorsPipeline;
    this.recursionGuard = recursionGuard;
  }

  @Override
  public <T> T generateInstanceOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    try {
      recursionGuard.addDepthLevelTo(instanceType);
      //todo do we need this method?
      return recursionGuard.generateUsing(generatorsPipeline, instanceType, fixture);
    } finally {
      recursionGuard.removeDepthLevelFor(instanceType);
    }

  }

  @Override
  public <T> T generateEmptyInstanceOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return generatorsPipeline.generateEmptyInstanceOf(instanceType, fixture);
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
