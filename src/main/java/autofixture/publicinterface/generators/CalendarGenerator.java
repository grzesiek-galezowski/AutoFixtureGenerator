package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarGenerator implements InstanceGenerator {
  int secondsToAdd = 0;

  @Override
  public <T> boolean appliesTo(InstanceType<T> typeToken) {
    return typeToken.isRawTypeAssignableFrom(Calendar.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.SECOND, secondsToAdd++);
    return (T) calendar;
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }
}
