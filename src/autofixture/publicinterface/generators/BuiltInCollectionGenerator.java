package autofixture.publicinterface.generators;

import java.util.Collection;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class BuiltInCollectionGenerator implements InstanceGenerator {

	@Override
	public <T> boolean appliesTo(InstanceType<T> clazz) {
		return 
				clazz.isAssignableTo(Collection.class)
				|| Iterable.class.isAssignableFrom(clazz.getRawType());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> T next(InstanceType<T> type, FixtureContract fixture) {
		//TODO make same as array generation
		Collection collection = type.createCollection();
		
		InstanceType<?> nestedGenericType = type.getNestedGenericType();
		Object instance1 = fixture.create(nestedGenericType);
		Object instance2 = fixture.create(nestedGenericType);
		Object instance3 = fixture.create(nestedGenericType);
		
		collection.add(instance1);
		collection.add(instance2);
		collection.add(instance3);
		
		return (T) collection;
	}



}
