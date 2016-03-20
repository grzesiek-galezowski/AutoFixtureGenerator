package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by astral on 08.02.15.
 */
public class InstantGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Instant.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) Instant.now().plus(Duration.ofNanos(fixture.create(Long.class)));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
