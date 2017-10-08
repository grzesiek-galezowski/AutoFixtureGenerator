package autofixture.publicinterface.inline;

import autofixture.implementationdetails.InstanceCursor;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineConstrainedGenerator;
import com.google.common.reflect.TypeToken;

public class FromGenerator<T> implements InlineConstrainedGenerator<T> {
  private final T[] possibleValues;
  private InstanceCursor cursor;

  public FromGenerator(final InstanceCursor instanceCursor,
                       final T... possibleValues) {
    this.possibleValues = possibleValues;
    cursor = instanceCursor;

  }

  public T next(final TypeToken<T> type, FixtureContract fixture) {
    final T returnedValue = cursor.selectFrom(possibleValues);

    cursor.advance();
    cursor.save();

    return returnedValue;
  }

}
