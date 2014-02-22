package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.Fixture;

public interface InstanceGenerator {
	<T> boolean AppliesTo(TypeToken<T> typeToken);
	<T> T next(TypeToken<T> typeToken, Fixture fixture);
}
