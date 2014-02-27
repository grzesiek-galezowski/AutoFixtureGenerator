package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class ByteAndCharGenerator implements InstanceGenerator {

	public Byte startingByte = 1;

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(byte.class)
				|| typeToken.isRawTypeAssignableFrom(Byte.class)
				|| typeToken.isRawTypeAssignableFrom(char.class)
				|| typeToken.isRawTypeAssignableFrom(Character.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T)(startingByte++);
	}

}
