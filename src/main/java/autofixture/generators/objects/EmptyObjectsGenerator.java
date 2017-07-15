package autofixture.generators.objects;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

/**
 * Created by grzes on 22.03.2017.
 */
public class EmptyObjectsGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return true;
  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return null;
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
