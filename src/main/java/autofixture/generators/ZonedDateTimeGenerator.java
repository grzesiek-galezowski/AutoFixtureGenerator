package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.time.Period;
import java.time.ZonedDateTime;

/**
 * Created by astral on 08.02.15.
 */
public class ZonedDateTimeGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ZonedDateTime.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) ZonedDateTime.now().plus(Period.ofDays(fixture.create(Integer.class)));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
