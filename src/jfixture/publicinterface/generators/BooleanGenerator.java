package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class BooleanGenerator implements InstanceGenerator {
	private Boolean currentValue = false;
	
	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.isAssignableFrom(boolean.class)
				|| typeToken.isAssignableFrom(Boolean.class);
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		currentValue = !currentValue;
		return (T) currentValue;
	}
}
