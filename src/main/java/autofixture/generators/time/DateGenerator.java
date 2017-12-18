package autofixture.generators.time;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Calendar;
import java.util.Date;

public class DateGenerator implements InstanceGenerator {

  private final Calendar calendar = Calendar.getInstance();

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isRawTypeAssignableFrom(Date.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    calendar.add(Calendar.SECOND, 1);
    return (T) calendar.getTime();
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
