package autofixture.publicinterface;


@SuppressWarnings("serial")
public class ObjectCreationException extends RuntimeException {

	public ObjectCreationException(InstanceType<?> instanceType) {
		super("Cannot create an instance of " + instanceType);
	}

	public ObjectCreationException(InstanceType<?> typeToken, Exception e) {
		super("Cannot create an instance of " + typeToken, e);
	}

	public ObjectCreationException(InstanceType<?> typeToken, String additionalDetails) {
		super("Cannot create an instance of " + typeToken + ". \n" + additionalDetails );
	}

	

}
