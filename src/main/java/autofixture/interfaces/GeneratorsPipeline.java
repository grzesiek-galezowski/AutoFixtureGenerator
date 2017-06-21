package autofixture.interfaces;

import autofixture.generators.InstanceGenerator;

public interface GeneratorsPipeline {
  <T> T executeFor(InstanceType<T> instanceType, FixtureContract fixture);

  void registerCustomization(InstanceGenerator instanceGenerator);

  void clearCustomizations();

  void setOmittingAutoProperties(boolean isOn);
}
