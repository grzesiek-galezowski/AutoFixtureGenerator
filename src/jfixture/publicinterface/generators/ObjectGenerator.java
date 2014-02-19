package jfixture.publicinterface.generators;

public interface ObjectGenerator {
	boolean AppliesTo(Class<?> clazz);
	Object next();
}
