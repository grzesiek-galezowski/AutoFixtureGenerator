package jfixture.publicinterface.generators;

import java.util.UUID;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class StringGenerator implements InstanceGenerator {
	public <T> T next(TypeToken<T> clazz, Fixture fixture) {
		return (T)UUID.randomUUID().toString();
	}

	@Override
	public <T> boolean AppliesTo(TypeToken<T> clazz) {
		return clazz.getRawType() == String.class;
	}
	
}
