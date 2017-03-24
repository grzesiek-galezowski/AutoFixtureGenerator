package autofixture.publicinterface.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by astral on 08.02.15.
 */
public class LocalDateTimeGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(LocalDateTime.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) LocalDateTime.now().plus(Duration.ofNanos(fixture.create(Long.class)));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
