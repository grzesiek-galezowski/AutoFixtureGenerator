package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Collection;
import java.util.function.Function;

public class VavrCollectionGenerator<TCollection> implements InstanceGenerator {

  private Class<TCollection> clazz;
  private Function<Collection<?>, TCollection> collectionFactory;

  public VavrCollectionGenerator(Class<TCollection> clazz, Function<Collection<?>, TCollection> factory) {
    this.clazz = clazz;
    collectionFactory = factory;
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(clazz);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    Collection<?> elements = instanceType.turnIntoCollection(fixture);
    return (T) collectionFactory.apply(elements);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

