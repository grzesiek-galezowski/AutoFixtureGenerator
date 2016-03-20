package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

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
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
