package autofixture.publicinterface.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.UUID;

public class StringGenerator implements InstanceGenerator {

  @SuppressWarnings("unchecked")
  public <T> T next(final InstanceType<T> clazz, final FixtureContract fixture) {
    return (T) UUID.randomUUID().toString();
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> clazz) {
    return clazz.isRawTypeAssignableFrom(String.class);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
