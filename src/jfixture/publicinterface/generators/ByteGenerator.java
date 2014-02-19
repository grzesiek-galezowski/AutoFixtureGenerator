package jfixture.publicinterface.generators;

public class ByteGenerator implements ObjectGenerator {

	public byte startingByte = 1;
	public Object next() {
		return startingByte++;
	}
	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == byte.class
				|| clazz == Byte.class;
	}

}
