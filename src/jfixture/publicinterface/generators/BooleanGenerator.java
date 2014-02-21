package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public class BooleanGenerator implements PrimitiveGenerator {
	private Boolean currentValue = false;
	
	public Object next() {
		currentValue = !currentValue;
		return currentValue;
	}

	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.isAssignableFrom(boolean.class)
				|| clazz.isAssignableFrom(Boolean.class);
	}
}
