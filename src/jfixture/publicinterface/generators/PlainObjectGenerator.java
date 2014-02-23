package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class PlainObjectGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(Object.class);
	}

	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		return (T)new Object();
	}
}
