package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.time.Clock;
import java.time.ZoneId;

/**
 * Created by astral on 08.02.15.
 */
public class ClockGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Clock.class);
  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return (T)Clock.tickMinutes(fixture.create(ZoneId.class));
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
