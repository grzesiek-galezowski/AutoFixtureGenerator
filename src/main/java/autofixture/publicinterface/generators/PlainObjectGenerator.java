package autofixture.publicinterface.generators;

import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.FixtureContract;

public class PlainObjectGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isRawTypeAssignableFrom(Object.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    return (T) new Object();
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
