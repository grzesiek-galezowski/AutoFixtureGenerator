package autofixture.interfaces;

import com.google.common.reflect.TypeToken;

/**
 * Created by grzes on 20.03.2017.
 */
public interface InstanceField<T> {
    TypeToken<?> resolveActualType();

    void setValueUsing(FixtureContract fixture) throws IllegalAccessException;
}
