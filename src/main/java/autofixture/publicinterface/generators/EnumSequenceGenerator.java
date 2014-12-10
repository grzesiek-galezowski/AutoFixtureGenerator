package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class EnumSequenceGenerator implements InstanceGenerator {

  private EnumCache enumCache;

  public EnumSequenceGenerator(EnumCache enumCache) {
    this.enumCache = enumCache;
  }

  @Override
  public <T> boolean appliesTo(InstanceType<T> typeToken) {
    return typeToken.isEnum();
  }

  @Override
  public <T> T next(InstanceType<T> type, FixtureContract fixture) {
    enumCache.registerIfNotPresent(type);
    return enumCache.retrieveNextValueOf(type);
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }

}
