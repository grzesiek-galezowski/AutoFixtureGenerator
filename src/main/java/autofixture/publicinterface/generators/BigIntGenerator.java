package autofixture.publicinterface.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.math.BigInteger;

public class BigIntGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isAssignableFrom(BigInteger.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    return (T) new BigInteger(fixture.create(Integer.class).toString());
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
