package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceType;

public class ArrayGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> clazz) {
    return clazz.isArray();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> type, final FixtureContract fixture) {
    final InstanceType<?> componentType = type.getArrayElementType();
    final Object array = componentType.createArray(fixture.createMany(componentType).toArray());
    final T stronglyTypedArray = (T) array;
    return stronglyTypedArray;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
