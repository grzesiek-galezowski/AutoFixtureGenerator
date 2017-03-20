package autofixture.interfaces;

public interface InstanceGenerator {
  <T> boolean appliesTo(InstanceType<T> instanceType);

  @SuppressWarnings("unchecked")
  <T> T next(InstanceType<T> instanceType, FixtureContract fixture);

  void setOmittingAutoProperties(boolean isOn);
}
