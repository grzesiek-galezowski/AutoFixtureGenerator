package jfixture.publicinterface;

import com.google.common.reflect.TypeToken;

public class ObjectCreationException extends RuntimeException {

	public ObjectCreationException(TypeToken<?> typeToken) {
		super("Cannot create an instance of " + typeToken);
	}

	public ObjectCreationException(TypeToken<?> typeToken, 	ReflectiveOperationException e) {
		super("Cannot create an instance of " + typeToken, e);
	}

	

}
