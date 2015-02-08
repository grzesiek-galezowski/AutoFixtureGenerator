package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by astral on 08.02.15.
 */
public class LocalDateGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(LocalDate.class);
  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return (T)(LocalDate.now().plus(Period.ofDays(fixture.create(Integer.class))));
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
