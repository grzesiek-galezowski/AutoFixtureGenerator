package jfixture.publicinterface.generators;

import java.util.Random;

import com.google.common.reflect.TypeToken;

public class EnumGenerator {

	public boolean AppliesTo(TypeToken<?> typeToken) {
		return typeToken.getRawType().isEnum();
	}

	public Object next(TypeToken<?> typeToken) {
		Object[] enumElements=typeToken.getRawType().getEnumConstants();
		int idx = new Random().nextInt(enumElements.length);
		return enumElements[idx];
		//TODO make it sequential! Random is bad...
	}

}
