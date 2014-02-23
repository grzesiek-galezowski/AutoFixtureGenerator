package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

import com.google.common.reflect.TypeToken;

public class DoubleGenerator implements InstanceGenerator {
	private Double startingNumber = 0.3;

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(double.class)
				|| typeToken.isRawTypeAssignableFrom(Double.class)
				|| typeToken.isRawTypeAssignableFrom(float.class)
				|| typeToken.isRawTypeAssignableFrom(Float.class); 
	}
	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		return (T)(startingNumber++);
	}
}
