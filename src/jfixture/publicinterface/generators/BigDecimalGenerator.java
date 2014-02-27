package jfixture.publicinterface.generators;

import java.math.BigDecimal;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class BigDecimalGenerator implements InstanceGenerator {

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isAssignableFrom(BigDecimal.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T) new BigDecimal(fixture.create(Integer.class));
	}
}
