package autofixture.generators.vavr;

import autofixture.generators.collections.BuiltInCollectionGenerator;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.List;
import io.vavr.collection.Multimap;

public class VavrListGenerator implements InstanceGenerator {
  private final BuiltInCollectionGenerator gen;

  public VavrListGenerator() {
    gen = new BuiltInCollectionGenerator();
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.List.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T)List.ofAll(fixture.createMany(instanceType.getNestedGenericType1()));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
