package jfixture.publicinterface.generators;

import java.util.UUID;

public class StringGenerator implements ObjectGenerator {
	public Object next() {
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == String.class;
	}
	
}
