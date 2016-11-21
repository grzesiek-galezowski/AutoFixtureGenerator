package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

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
