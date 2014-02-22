package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class PlainObjectGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.getRawType() == Object.class;
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		return (T)new Object();
	}
}
