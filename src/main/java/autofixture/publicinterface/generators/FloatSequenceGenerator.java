package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class FloatSequenceGenerator implements InstanceGenerator {
  private Float startingNumber = new Float(0.6);

  @Override
  public <T> boolean appliesTo(InstanceType<T> typeToken) {
    return typeToken.isCompatibleWithAnyOf(Float.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
    return (T) (startingNumber++);
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }
}
