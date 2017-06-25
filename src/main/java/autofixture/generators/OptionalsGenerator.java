package autofixture.generators;

import autofixture.exceptions.ObjectCreationException;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * Created by astral on 02.10.14.
 */
public class OptionalsGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Optional.class)
        || instanceType.isRawTypeAssignableFrom(OptionalInt.class)
        || instanceType.isRawTypeAssignableFrom(OptionalDouble.class)
        || instanceType.isRawTypeAssignableFrom(OptionalLong.class);

  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    if (instanceType.isRawTypeAssignableFrom(Optional.class)) {
      return (T) Optional.of(fixture.create(instanceType.getNestedGenericType1()));
    } else if (instanceType.isRawTypeAssignableFrom(OptionalInt.class)) {
      return (T) OptionalInt.of(fixture.create(Integer.class));
    } else if (instanceType.isRawTypeAssignableFrom(OptionalLong.class)) {
      return (T) OptionalLong.of(fixture.create(Long.class));
    } else if (instanceType.isRawTypeAssignableFrom(OptionalDouble.class)) {
      return (T) OptionalDouble.of(fixture.create(Double.class));
    } else {
      throw new ObjectCreationException(instanceType);
    }
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
