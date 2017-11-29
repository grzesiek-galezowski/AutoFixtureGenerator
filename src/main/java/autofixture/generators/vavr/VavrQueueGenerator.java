package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.Queue;

import java.util.Collection;

public class VavrQueueGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.Queue.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    Collection many = fixture.createMany(instanceType.getNestedGenericType1());
    return (T) Queue.ofAll(many);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
