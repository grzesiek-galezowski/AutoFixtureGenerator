package jfixture.publicinterface.generators;

import java.math.BigInteger;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class BigIntGenerator implements InstanceGenerator {
	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isAssignableFrom(BigInteger.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T) new BigInteger(fixture.create(Integer.class).toString());
	}
}
