package jfixture.publicinterface.generators;

import java.util.UUID;

import com.google.common.reflect.TypeToken;

public class StringGenerator implements ObjectGenerator {
	public Object next() {
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.getRawType() == String.class;
	}
	
}
