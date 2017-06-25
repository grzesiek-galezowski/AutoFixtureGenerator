package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.Collection;

/**
 * Created by grzes on 22.03.2017.
 */
public class EmptyCollectionsGenerator implements InstanceGenerator {
    @Override
    public <T> boolean appliesTo(final InstanceType<T> clazz) {
        return
                clazz.isAssignableTo(Collection.class)
                        || Iterable.class.isAssignableFrom(clazz.getRawType());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> T next(final InstanceType<T> type, final FixtureContract fixture) {
        final Collection collection = type.createCollection(0);
        return (T) collection;
    }

    @Override
    public void setOmittingAutoProperties(final boolean isOn) {
    }

}
