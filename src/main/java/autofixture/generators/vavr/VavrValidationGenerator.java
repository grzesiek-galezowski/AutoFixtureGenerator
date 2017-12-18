package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.control.Validation;

import java.util.HashMap;
import java.util.Map;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

public class VavrValidationGenerator implements InstanceGenerator {

  private Map<InstanceType, Validation> lastValidationByType = new HashMap<>();

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Validation.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    if(lastValidationByType.containsKey(instanceType)) {
      Validation validation = lastValidationByType.get(instanceType);
      if(validation.isValid()) {
        validation = invalid(fromFirstGenericOf(instanceType, fixture));
      } else {
        validation = valid(fromSecondGenericOf(instanceType, fixture));
      }
      lastValidationByType.put(instanceType, validation);
      return (T) validation;
    } else {
      Validation validation = valid(fromSecondGenericOf(instanceType, fixture));
      lastValidationByType.put(instanceType, validation);
      return (T) validation;
    }

  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) Validation.valid(fixture.createDummy(instanceType.getNestedGenericType2()));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }

  private <T> Object fromSecondGenericOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return fixture.create(instanceType.getNestedGenericType2());
  }

  private <T> Object fromFirstGenericOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return fixture.create(instanceType.getNestedGenericType1());
  }
}


