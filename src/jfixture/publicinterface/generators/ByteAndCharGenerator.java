package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class ByteAndCharGenerator implements InstanceGenerator {

	public Byte startingByte = 1;

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(byte.class)
				|| typeToken.isRawTypeAssignableFrom(Byte.class)
				|| typeToken.isRawTypeAssignableFrom(char.class)
				|| typeToken.isRawTypeAssignableFrom(Character.class);
	}
	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		return (T)(startingByte++);
	}

}
