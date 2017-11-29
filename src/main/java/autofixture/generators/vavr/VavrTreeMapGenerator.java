package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.TreeMap;


public class VavrTreeMapGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(
        io.vavr.collection.TreeMap.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {

    java.util.Map map = new java.util.HashMap<>();
    for(int i = 0 ; i < fixture.getRepeatCount() ; ++i) {
      map.put(fixture.create(instanceType.getNestedGenericType1()),
          fixture.create(instanceType.getNestedGenericType2()));
    }

    return (T) (TreeMap<?, ?>) TreeMap.ofAll(map);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
