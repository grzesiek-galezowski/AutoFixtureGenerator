package autofixture.publicinterface;

public interface InstanceGenerator {
  <T> boolean appliesTo(InstanceType<T> instanceType);

  <T> T next(InstanceType<T> instanceType, FixtureContract fixture);

  void setOmittingAutoProperties(boolean isOn);
}
