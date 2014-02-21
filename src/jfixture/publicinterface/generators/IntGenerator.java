package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public class IntGenerator implements PrimitiveGenerator {

	public int startingInteger = 1;
	public Object next() {
		return startingInteger++;
	}
	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.getRawType() == int.class
				|| clazz.getRawType() == Integer.class
				|| clazz.getRawType() == short.class
				|| clazz.getRawType() == Short.class
				|| clazz.getRawType() == long.class
				|| clazz.getRawType() == Long.class;
	}

}
