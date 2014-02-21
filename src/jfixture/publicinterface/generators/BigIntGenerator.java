package jfixture.publicinterface.generators;

import java.math.BigInteger;

import com.google.common.reflect.TypeToken;

public class BigIntGenerator implements PrimitiveGenerator {
	private IntGenerator intGenerator = new IntGenerator();
	
	public Object next() {
		return new BigInteger(intGenerator.next().toString());
	}

	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.isAssignableFrom(BigInteger.class);
	}
}
