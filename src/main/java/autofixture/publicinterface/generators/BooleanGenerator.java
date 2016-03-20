package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class BooleanGenerator implements InstanceGenerator {
  private Boolean currentValue = false;

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isCompatibleWith(Boolean.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    currentValue = !currentValue;
    return (T) currentValue;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
