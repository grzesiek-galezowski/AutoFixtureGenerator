package jfixture.publicinterface.generators;

import java.math.BigDecimal;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class BigDecimalGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.isAssignableFrom(BigDecimal.class);
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		return (T) new BigDecimal(fixture.create(TypeToken.of(Integer.class)));
	}
}
