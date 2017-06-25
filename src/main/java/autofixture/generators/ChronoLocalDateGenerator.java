package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;

/**
 * Created by astral on 08.02.15.
 */
public class ChronoLocalDateGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ChronoLocalDate.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) LocalDate.now().plus(Period.ofWeeks(fixture.create(Integer.class)));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
