package jfixture.publicinterface.generators;

import java.math.BigInteger;

public class BigIntGenerator implements ObjectGenerator {
	private IntGenerator intGenerator = new IntGenerator();
	
	public Object next() {
		return new BigInteger(intGenerator.next().toString());
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == BigInteger.class;
	}
}
