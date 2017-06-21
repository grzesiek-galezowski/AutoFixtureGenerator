package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceType;

public interface InstanceGenerator {
  <T> boolean appliesTo(InstanceType<T> instanceType);

  @SuppressWarnings("unchecked")
  <T> T next(InstanceType<T> instanceType, FixtureContract fixture);

  void setOmittingAutoProperties(boolean isOn);
}
