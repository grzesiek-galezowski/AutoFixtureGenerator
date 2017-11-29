package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.PriorityQueue;

import java.util.Collection;

public class VavrPriorityQueueGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.PriorityQueue.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    Collection many = fixture.createMany(instanceType.getNestedGenericType1());
    return (T) PriorityQueue.ofAll(many);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
