package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.HashSet;

public class VavrHashSetGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(
        io.vavr.collection.HashSet.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) HashSet.ofAll(fixture.createMany(instanceType.getNestedGenericType1()));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }

}