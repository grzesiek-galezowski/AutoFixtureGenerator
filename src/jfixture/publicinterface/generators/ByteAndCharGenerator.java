package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public class ByteAndCharGenerator implements ObjectGenerator {

	public byte startingByte = 1;
	public Object next() {
		return startingByte++;
	}
	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.getRawType() == byte.class
				|| clazz.getRawType() == Byte.class
				|| clazz.getRawType() == char.class
				|| clazz.getRawType() == Character.class;
	}

}
