package jfixture.publicinterface.generators;

import java.math.BigInteger;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class BigIntGenerator implements InstanceGenerator {
	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isAssignableFrom(BigInteger.class);
	}

	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		return (T) new BigInteger(fixture.create(Integer.class).toString());
	}
}
