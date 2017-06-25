package autofixture.interfaces;

public interface GeneratorsPipeline {
  <T> T executeFor(InstanceType<T> instanceType, FixtureContract fixture);

  void registerCustomization(InstanceGenerator instanceGenerator);

  void clearCustomizations();

  void setOmittingAutoProperties(boolean isOn);
}
