package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.control.Either;
import io.vavr.control.Validation;

public class VavrEitherGenerator implements InstanceGenerator {

  private Either currentValue = Either.left("WHATEVER");

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Either.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    currentValue = currentValue.flatMap(r -> Either.left(fixture.create(instanceType.getNestedGenericType1()))
        .mapLeft(l -> Either.right(fixture.create(instanceType.getNestedGenericType2()))));
    return (T)currentValue;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

