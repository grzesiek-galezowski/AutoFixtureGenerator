package autofixture.generators;

import autofixture.interfaces.FixtureContract;
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
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
