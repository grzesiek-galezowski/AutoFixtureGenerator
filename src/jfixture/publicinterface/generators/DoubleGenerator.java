package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class DoubleGenerator implements InstanceGenerator {
	private Double startingNumber = 0.3;

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.getRawType() == double.class
				|| typeToken.getRawType() == Double.class
				|| typeToken.getRawType() == float.class
				|| typeToken.getRawType() == Float.class; 
	}
	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		return (T)(startingNumber++);
	}
}
