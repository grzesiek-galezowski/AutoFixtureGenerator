package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

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
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
