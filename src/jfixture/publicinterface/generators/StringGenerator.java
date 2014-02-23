package jfixture.publicinterface.generators;

import java.util.UUID;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class StringGenerator implements InstanceGenerator {
	public <T> T next(InstanceType<T> clazz, Fixture fixture) {
		return (T)UUID.randomUUID().toString();
	}

	@Override
	public <T> boolean AppliesTo(InstanceType<T> clazz) {
		return clazz.isRawTypeAssignableFrom(String.class);
	}
	
}
