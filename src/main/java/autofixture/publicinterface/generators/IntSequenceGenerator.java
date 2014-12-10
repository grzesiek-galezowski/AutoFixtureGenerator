package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class IntSequenceGenerator implements InstanceGenerator {

  public Integer startingInteger = 1;

  @Override
  public <T> boolean appliesTo(InstanceType<T> typeToken) {
    return typeToken.isCompatibleWithAnyOf(
      Integer.class,
      Short.class,
      Long.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
    return (T) (startingInteger++);
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }

}
