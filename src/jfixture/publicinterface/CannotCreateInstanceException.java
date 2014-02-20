package jfixture.publicinterface;

public class CannotCreateInstanceException extends RuntimeException {

	public CannotCreateInstanceException(Class<?> clazz) {
		super("Cannot create an instance of " + clazz);
	}

	

}
