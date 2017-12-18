package autofixture.interfaces;

public interface GeneratorsPipeline {
  <T> T generateInstanceOf(InstanceType<T> instanceType, FixtureContract fixture);

  <T> T generateEmptyInstanceOf(InstanceType<T> instanceType, FixtureContract fixture);

  void registerCustomization(InstanceGenerator instanceGenerator);

  void clearCustomizations();

  void setOmittingAutoProperties(boolean isOn);
}
