package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

public interface CollectionGenerator {
	<T> boolean AppliesTo(Class<T> clazz);
	<T> T next(Class<T> clazz, Fixture fixture) throws InstantiationException, IllegalAccessException;
}
