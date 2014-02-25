package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;

public class ArrayGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> clazz) {
		return clazz.isArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> type, FixtureContract fixture) {
		InstanceType<?> componentType = type.getArrayElementType();
		
		Object array = componentType.createArray(new Object[] {
			fixture.create(componentType), 
			fixture.create(componentType), 
			fixture.create(componentType)
		});
		
		return (T)array;
	}

}
