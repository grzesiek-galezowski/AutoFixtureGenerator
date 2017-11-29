package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.control.Either;
import io.vavr.control.Validation;

public class VavrValidationGenerator implements InstanceGenerator {

  private Validation currentValue = Validation.invalid("WHATEVER");

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Validation.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    currentValue = currentValue.flatMap(r -> Validation.invalid(fixture.create(instanceType.getNestedGenericType1()))
        .mapError(l -> Validation.valid(fixture.create(instanceType.getNestedGenericType2()))));
    return (T)currentValue;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
