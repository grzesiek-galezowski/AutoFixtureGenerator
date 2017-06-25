package autofixture.publicinterface.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.publicinterface.constraints.GenerationConstraint;
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
