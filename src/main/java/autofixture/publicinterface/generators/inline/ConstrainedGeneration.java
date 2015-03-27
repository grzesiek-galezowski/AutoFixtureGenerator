package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.GenerationConstraint;
import autofixture.publicinterface.InlineInstanceGenerator;
import com.google.common.reflect.TypeToken;

public class ConstrainedGeneration<T> implements InlineInstanceGenerator<T> {

  private final GenerationConstraint<T> constraint;
  private final TypeToken<T> typeToken;

  public ConstrainedGeneration(TypeToken<T> typeToken, GenerationConstraint<T> constraint) {
    this.constraint = constraint;
    this.typeToken = typeToken;
  }

  @Override
  public T next(FixtureContract fixture) {
    T currentValue;
    do {
      currentValue = fixture.create(typeToken);
    } while (constraint.doesNotAccept(currentValue));
    return currentValue;
  }


}
