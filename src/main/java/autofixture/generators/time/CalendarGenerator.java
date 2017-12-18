package autofixture.generators.time;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarGenerator implements InstanceGenerator {
  private int secondsToAdd = 0;

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return typeToken.isRawTypeAssignableFrom(Calendar.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> typeToken, final FixtureContract fixture) {
    final Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.SECOND, secondsToAdd++);
    return (T) calendar;
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
