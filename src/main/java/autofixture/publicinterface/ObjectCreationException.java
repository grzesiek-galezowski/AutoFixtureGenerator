package autofixture.publicinterface;


@SuppressWarnings("serial")
public class ObjectCreationException extends RuntimeException {

  public ObjectCreationException(final InstanceType<?> instanceType) {
    super("Cannot create an instance of " + instanceType);
  }

  public ObjectCreationException(final InstanceType<?> typeToken, final Exception e) {
    super("Cannot create an instance of " + typeToken, e);
  }

  public ObjectCreationException(final InstanceType<?> typeToken, final String additionalDetails) {
    super("Cannot create an instance of " + typeToken + ". \n" + additionalDetails);
  }

  public ObjectCreationException(final InstanceType<?> typeToken, final String additionalDetails, final Throwable e) {
    super("Cannot create an instance of " + typeToken + ". \n" + additionalDetails, e);
  }


}
