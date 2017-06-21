package autofixture.publicinterface.inline;

import autofixture.interfaces.FixtureContract;
import com.google.common.reflect.TypeToken;

/**
 * Created by astral on 28.03.15.
 */
public interface InlineConstrainedGenerator<T> {
  T next(TypeToken<T> type, FixtureContract fixture);
}
