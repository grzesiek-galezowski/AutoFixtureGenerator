package autofixture.generators.vavr;

import autofixture.generators.collections.BuiltInCollectionGenerator;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.Array;

public class VavrArrayGenerator implements InstanceGenerator {
  private BuiltInCollectionGenerator gen = new BuiltInCollectionGenerator();

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.Array.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) Array.ofAll(
        fixture.createMany(instanceType.getNestedGenericType1()));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

