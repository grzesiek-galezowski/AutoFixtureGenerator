package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public interface GeneratorsPipeline {
  <T> T executeFor(InstanceType<T> instanceType, FixtureContract fixture);

  void registerCustomization(InstanceGenerator instanceGenerator);

  void clearCustomizations();

  void setOmittingAutoProperties(boolean isOn);
}
