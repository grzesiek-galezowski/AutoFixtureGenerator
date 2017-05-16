package autofixture.publicinterface.generators;

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
    final InstanceType<?> nestedGenericType1 = type.getNestedGenericType1();
    final InstanceType<?> nestedGenericType2 = type.getNestedGenericType2();
    for(int i = 0 ; i < fixture.getRepeatCount() ; ++i) {
      map.put(fixture.create(nestedGenericType1), fixture.create(nestedGenericType2));
    }
    return (T) map;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }


}
