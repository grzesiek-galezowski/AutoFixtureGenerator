package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.util.Collection;

public class BuiltInCollectionGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(InstanceType<T> clazz) {
    return
      clazz.isAssignableTo(Collection.class)
        || Iterable.class.isAssignableFrom(clazz.getRawType());
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public <T> T next(InstanceType<T> type, FixtureContract fixture) {
    //TODO make same as array generation
    Collection collection = type.createCollection(fixture.getRepeatCount());
    collection.addAll(fixture.createMany(type.getNestedGenericType()));
    return (T) collection;
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {
  }


}
