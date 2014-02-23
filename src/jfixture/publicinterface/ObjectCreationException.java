package jfixture.publicinterface;

import com.google.common.reflect.TypeToken;

public class ObjectCreationException extends RuntimeException {

	public ObjectCreationException(InstanceType<?> instanceType) {
		super("Cannot create an instance of " + instanceType);
	}

	public ObjectCreationException(InstanceType<?> typeToken, ReflectiveOperationException e) {
		super("Cannot create an instance of " + typeToken, e);
	}

	public ObjectCreationException(InstanceType<?> typeToken, String additionalDetails) {
		super("Cannot create an instance of " + typeToken + ". " + additionalDetails );
	}

	

}
