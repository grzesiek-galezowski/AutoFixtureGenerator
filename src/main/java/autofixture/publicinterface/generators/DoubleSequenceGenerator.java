package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class DoubleSequenceGenerator implements InstanceGenerator {
  private static final Double ARBITRARY_DOUBLE = 0.3;
  private Double startingNumber = ARBITRARY_DOUBLE;

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isCompatibleWithAnyOf(Double.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    return (T) (startingNumber++);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
