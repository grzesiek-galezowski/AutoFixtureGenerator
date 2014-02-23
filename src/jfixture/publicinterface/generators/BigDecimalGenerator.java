package jfixture.publicinterface.generators;

import java.math.BigDecimal;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class BigDecimalGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isAssignableFrom(BigDecimal.class);
	}

	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		return (T) new BigDecimal(fixture.create(Integer.class));
	}
}
