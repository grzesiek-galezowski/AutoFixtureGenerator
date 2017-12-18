package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import com.google.common.base.Supplier;

import java.util.Collection;
import java.util.function.Function;

public class VavrCollectionGenerator<TCollection> implements InstanceGenerator {

  private Class<TCollection> clazz;
  private Function<Collection<?>, TCollection> collectionFactory;
  private Supplier<TCollection> emptyFactory;

  public VavrCollectionGenerator(
      Class<TCollection> clazz,
      Function<Collection<?>, TCollection> factory,
      Supplier<TCollection> emptyFactory) {
    this.clazz = clazz;
    collectionFactory = factory;
    this.emptyFactory = emptyFactory;
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
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) emptyFactory.get();
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

