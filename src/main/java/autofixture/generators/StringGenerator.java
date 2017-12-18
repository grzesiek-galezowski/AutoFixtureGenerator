package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.UUID;

public class StringGenerator implements InstanceGenerator {

  @Override
  @SuppressWarnings("unchecked")
  public <T> T next(final InstanceType<T> clazz, final FixtureContract fixture) {
    return (T) UUID.randomUUID().toString();
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> clazz) {
    return clazz.isRawTypeAssignableFrom(String.class);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
