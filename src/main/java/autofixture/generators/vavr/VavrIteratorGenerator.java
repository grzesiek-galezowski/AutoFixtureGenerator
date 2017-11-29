package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.List;

public class VavrIteratorGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.Iterator.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) List.ofAll(instanceType.turnIntoCollection(fixture)).iterator();
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

