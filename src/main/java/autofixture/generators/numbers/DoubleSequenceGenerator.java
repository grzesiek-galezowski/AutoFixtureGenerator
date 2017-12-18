package autofixture.generators.numbers;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

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
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
