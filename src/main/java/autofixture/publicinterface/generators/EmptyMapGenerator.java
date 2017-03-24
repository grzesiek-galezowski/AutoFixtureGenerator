package autofixture.publicinterface.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Map;

/**
 * Created by grzes on 24.03.2017.
 */
public class EmptyMapGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> clazz) {
    return clazz.isAssignableTo(Map.class);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public <T> T next(final InstanceType<T> type, final FixtureContract fixture) {
    //TODO make same as array generation
    final Map map = type.createMap(fixture.getRepeatCount());
    return (T) map;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }
}
