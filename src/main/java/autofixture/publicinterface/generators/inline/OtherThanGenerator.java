package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;
import autofixture.publicinterface.InstanceOf;

import java.util.Arrays;

public class OtherThanGenerator<T> implements InlineInstanceGenerator<T> {

  private final T[] omittedValues;

  public OtherThanGenerator(T[] omittedValues) {
    this.omittedValues = omittedValues;
  }

  @Override
  public T next(FixtureContract fixture) {
    T currentValue;
    do {
      currentValue = fixture.create(new InstanceOf<T>());
    } while (Arrays.asList(omittedValues).contains(currentValue));
    return currentValue;
  }

}
