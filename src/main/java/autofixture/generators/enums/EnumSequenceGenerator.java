package autofixture.generators.enums;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

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
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
