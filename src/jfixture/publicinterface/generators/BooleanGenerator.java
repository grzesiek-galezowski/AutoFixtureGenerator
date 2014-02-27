package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class BooleanGenerator implements InstanceGenerator {
	private Boolean currentValue = false;
	
	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isAssignableFrom(boolean.class)
				|| typeToken.isAssignableFrom(Boolean.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		currentValue = !currentValue;
		return (T) currentValue;
	}
}
