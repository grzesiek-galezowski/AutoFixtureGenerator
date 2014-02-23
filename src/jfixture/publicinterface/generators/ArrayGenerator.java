package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class ArrayGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> clazz) {
		return clazz.isArray();
	}

	@Override
	public <T> T next(InstanceType<T> type, Fixture fixture) {
		InstanceType<?> componentType = type.getArrayElementType();
		
		Object array = componentType.createArray(new Object[] {
			fixture.create(componentType), 
			fixture.create(componentType), 
			fixture.create(componentType)
		});
		
		return (T)array;
	}

}
