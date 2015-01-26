package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;
import com.google.common.reflect.TypeToken;

import java.util.Arrays;

public class OtherThanGenerator<T> implements InlineInstanceGenerator<T> {

  private final T[] omittedValues;
  private final TypeToken<T> typeToken;

  public OtherThanGenerator(TypeToken<T> typeToken, T[] omittedValues) {
    this.omittedValues = omittedValues;
    this.typeToken = typeToken;
  }

  @Override
  public T next(FixtureContract fixture) {
    T currentValue;
    do {
      currentValue = fixture.create(typeToken);
    } while (Arrays.asList(omittedValues).contains(currentValue));
    return currentValue;
  }

}
