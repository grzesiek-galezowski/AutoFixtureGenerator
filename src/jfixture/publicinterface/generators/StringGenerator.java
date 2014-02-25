package jfixture.publicinterface.generators;

import java.util.UUID;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;

public class StringGenerator implements InstanceGenerator {
	
	@SuppressWarnings("unchecked")
	public <T> T next(InstanceType<T> clazz, FixtureContract fixture) {
		return (T)UUID.randomUUID().toString();
	}

	@Override
	public <T> boolean AppliesTo(InstanceType<T> clazz) {
		return clazz.isRawTypeAssignableFrom(String.class);
	}
	
}
