package jfixture.publicinterface;

public class ObjectCreationException extends RuntimeException {

	public ObjectCreationException(Class<?> clazz) {
		super("Cannot create an instance of " + clazz);
	}

	

}
