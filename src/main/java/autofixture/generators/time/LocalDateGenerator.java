package autofixture.generators.time;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by astral on 08.02.15.
 */
public class LocalDateGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(LocalDate.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) (LocalDate.now().plus(Period.ofDays(fixture.create(Integer.class))));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
