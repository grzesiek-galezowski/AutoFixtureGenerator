package autofixture.generators.numbers;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

public class ByteAndCharSequenceGenerator implements InstanceGenerator {

  private Byte startingByte = 1;

  @Override
  public <T> boolean appliesTo(final InstanceType<T> type) {
    return type.isCompatibleWithAnyOf(Byte.class, Character.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    return (T) (startingByte++);
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
