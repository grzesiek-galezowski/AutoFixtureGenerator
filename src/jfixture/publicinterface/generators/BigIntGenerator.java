package jfixture.publicinterface.generators;

import java.math.BigInteger;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class BigIntGenerator implements InstanceGenerator {
	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.isAssignableFrom(BigInteger.class);
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		return (T) new BigInteger(fixture.create(typeToken.of(Integer.class)).toString());
	}
}
