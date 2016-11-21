package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class EnumSequenceGenerator implements InstanceGenerator {

  private final EnumCache enumCache;

  public EnumSequenceGenerator(final EnumCache enumCache) {
    this.enumCache = enumCache;
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isEnum();
  }

  @Override
  public <T> T next(final InstanceType<T> type, final FixtureContract fixture) {
    enumCache.registerIfNotPresent(type);
    return enumCache.retrieveNextValueOf(type);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
