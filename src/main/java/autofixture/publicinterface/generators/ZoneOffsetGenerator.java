package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.time.ZoneOffset;

/**
 * Created by astral on 08.02.15.
 */
public class ZoneOffsetGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ZoneOffset.class);
  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return (T)ZoneOffset.ofTotalSeconds(fixture.create(Integer.class));
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
