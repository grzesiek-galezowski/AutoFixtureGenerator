package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public class DoubleGenerator implements ObjectGenerator {
	private Double startingNumber = 0.3;
	public Object next() {
		return startingNumber++;
	}
	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.getRawType() == double.class
				|| clazz.getRawType() == Double.class
				|| clazz.getRawType() == float.class
			    || clazz.getRawType() == Float.class; 
	}
}
