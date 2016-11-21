package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.GenerationConstraint;
import autofixture.publicinterface.InlineConstrainedGenerator;
import com.google.common.reflect.TypeToken;

public class GenerationConstrainedByValueRejection<T> implements InlineConstrainedGenerator<T> {

  private final GenerationConstraint<T> constraint;

  public GenerationConstrainedByValueRejection(final GenerationConstraint<T> constraint) {
    this.constraint = constraint;
  }

  @Override
  public T next(final TypeToken<T> typeToken, final FixtureContract fixture) {
    T currentValue;
    do {
      currentValue = fixture.create(typeToken);
    } while (constraint.doesNotAccept(currentValue));
    return currentValue;
  }
}