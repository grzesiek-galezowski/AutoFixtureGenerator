package jfixture.publicinterface.generators;

import java.util.Collection;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;

public class BuiltInCollectionGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> clazz) {
		return clazz.IsAssignableTo(Collection.class);
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
