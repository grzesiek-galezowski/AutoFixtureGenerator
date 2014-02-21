package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.Fixture;

public interface CompoundObjectGenerator {
	<T> boolean AppliesTo(TypeToken<T> typeToken);
	<T> T next(TypeToken<T> typeToken, Fixture fixture) throws InstantiationException, IllegalAccessException;
}
