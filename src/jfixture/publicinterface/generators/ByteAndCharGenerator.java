package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class ByteAndCharGenerator implements InstanceGenerator {

	public Byte startingByte = 1;

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.getRawType() == byte.class
				|| typeToken.getRawType() == Byte.class
				|| typeToken.getRawType() == char.class
				|| typeToken.getRawType() == Character.class;
	}
	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		return (T)(startingByte++);
	}

}
