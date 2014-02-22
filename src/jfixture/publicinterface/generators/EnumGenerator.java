package jfixture.publicinterface.generators;

import java.util.Random;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class EnumGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.getRawType().isEnum();
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		Object[] enumElements=typeToken.getRawType().getEnumConstants();
		int idx = new Random().nextInt(enumElements.length);
		return (T)(enumElements[idx]);
		//TODO make it sequential! Random is bad...
	}

}
