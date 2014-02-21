package jfixture.publicinterface.generators;

import java.lang.reflect.Array;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.Fixture;

public class ArrayGenerator implements CollectionGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> clazz) {
		return clazz.isArray();
	}

	@Override
	public <T> T next(TypeToken<T> clazz, Fixture fixture) throws InstantiationException, IllegalAccessException {
		TypeToken<?> componentType = clazz.getComponentType();
		Object instance1 = fixture.create(componentType);
		Object instance2 = fixture.create(componentType);
		Object instance3 = fixture.create(componentType);
		
		Object array = Array.newInstance(componentType.getRawType(), 3);
		Array.set(array, 0, instance1);
		Array.set(array, 1, instance2);
		Array.set(array, 2, instance3);
		
		return (T)array;
	}

}
