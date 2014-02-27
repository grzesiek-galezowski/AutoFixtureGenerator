package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class PlainObjectGenerator implements InstanceGenerator {

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(Object.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T)new Object();
	}
}
