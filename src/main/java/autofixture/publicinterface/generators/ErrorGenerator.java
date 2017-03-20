package autofixture.publicinterface.generators;

import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.FixtureContract;

public class ErrorGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Error.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) new Error(fixture.create(String.class));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
