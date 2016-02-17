package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

/**
 * Created by astral on 08.02.15.
 */
public class ChronoLocalDateTimeGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ChronoLocalDateTime.class);
  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return (T) LocalDateTime.now().plus(Duration.ofNanos(fixture.create(Long.class)));
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
