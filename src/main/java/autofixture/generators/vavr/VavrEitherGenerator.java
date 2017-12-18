package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.control.Either;

import java.util.HashMap;
import java.util.Map;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class VavrEitherGenerator implements InstanceGenerator {

  private Map<InstanceType, Either> lastEitherByType = new HashMap<>();

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Either.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    if(lastEitherByType.containsKey(instanceType)) {
      Either either = lastEitherByType.get(instanceType);
      if(either.isRight()) {
        either = left(fromFirstGenericOf(instanceType, fixture));
      } else {
        either = right(fromSecondGenericOf(instanceType, fixture));
      }
      lastEitherByType.put(instanceType, either);
      return (T) either;
    } else {
      Either either = right(fromSecondGenericOf(instanceType, fixture));
      lastEitherByType.put(instanceType, either);
      return (T) either;
    }

  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T)Either.right(fixture.createDummy(instanceType.getNestedGenericType2()));
  }

  private <T> Object fromSecondGenericOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return fixture.create(instanceType.getNestedGenericType2());
  }

  private <T> Object fromFirstGenericOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return fixture.create(instanceType.getNestedGenericType1());
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
