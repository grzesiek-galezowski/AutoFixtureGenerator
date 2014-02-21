package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public class PlainObjectGenerator implements ObjectGenerator {
	public Object next() {
		return new Object();
	}

	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.getRawType() == Object.class;
	}
}
