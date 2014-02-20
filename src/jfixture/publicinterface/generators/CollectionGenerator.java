package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

public interface CollectionGenerator {
	boolean AppliesTo(Class<?> clazz);
	Object next(Class<?> clazz, Fixture fixture);
}
