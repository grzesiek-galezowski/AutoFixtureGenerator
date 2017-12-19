package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import com.google.common.base.Supplier;

import java.util.Map;
import java.util.function.Function;

public class VavrMapGenerator<TMap> implements InstanceGenerator {

  private Function<Map, TMap> mapFactory;
  private Class<TMap> clazz;
  private Supplier<TMap> emptySupplier;

  public VavrMapGenerator(Class<TMap> clazz,
                          Function<Map, TMap> multimapFactory,
                          final Supplier<TMap> emptySupplier) {
    mapFactory = multimapFactory;
    this.clazz = clazz;
    this.emptySupplier = emptySupplier;
  }

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(clazz);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    Map map = instanceType.turnIntoMapInstance(fixture);
    return (T) mapFactory.apply(map);
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T)emptySupplier.get();
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
