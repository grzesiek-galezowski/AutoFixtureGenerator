package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Map;
import java.util.function.Function;

public class VavrMapGenerator<TMap> implements InstanceGenerator {

  private Function<Map, TMap> mapFactory;
  private Class<TMap> clazz;

  public VavrMapGenerator(Class<TMap> clazz, Function<Map, TMap> multimapFactory) {
    mapFactory = multimapFactory;
    this.clazz = clazz;
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
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
