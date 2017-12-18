package autofixture.generators.collections;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Map;

public class BuiltInMapGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> clazz) {
    return clazz.isAssignableTo(Map.class);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public <T> T next(final InstanceType<T> type, final FixtureContract fixture) {
    //TODO make same as array generation
    final Map map = type.createEmptyMap();
    fillWithNestedInstances(map, type, fixture);
    return (T) map;
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    //TODO make same as array generation
    final Map map = instanceType.createEmptyMap();
    return (T) map;

  }

  public <T> void fillWithNestedInstances(final Map map, final InstanceType<T> type, final FixtureContract fixture) {
    final InstanceType<?> nestedGenericType1 = type.getNestedGenericType1();
    final InstanceType<?> nestedGenericType2 = type.getNestedGenericType2();
    for(int i = 0 ; i < fixture.getRepeatCount() ; ++i) {
      map.put(fixture.create(nestedGenericType1), fixture.create(nestedGenericType2));
    }
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }


}
