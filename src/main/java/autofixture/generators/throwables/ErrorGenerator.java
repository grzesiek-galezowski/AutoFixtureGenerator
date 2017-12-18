package autofixture.generators.throwables;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

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
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
