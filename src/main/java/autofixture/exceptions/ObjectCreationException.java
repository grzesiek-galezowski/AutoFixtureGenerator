package autofixture.exceptions;


import autofixture.interfaces.InstanceType;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("serial")
public class ObjectCreationException extends RuntimeException {

  public ObjectCreationException(final InstanceType<?> instanceType) {
    super(basicMessageFor(instanceType));
  }

  @NotNull
  private static String basicMessageFor(final InstanceType<?> instanceType) {
    return "Cannot create an instance of " + instanceType + ".";
  }

  public ObjectCreationException(final InstanceType<?> instanceType, final Exception e) {
    super(basicMessageFor(instanceType), e);
  }

  public ObjectCreationException(final InstanceType<?> instanceType, final String additionalDetails) {
    super(basicMessageFor(instanceType) +" \n" + additionalDetails);
  }

  public ObjectCreationException(final InstanceType<?> instanceType, final String additionalDetails, final Throwable e) {
    super(basicMessageFor(instanceType) + "."+" \n" + additionalDetails, e);
  }


}
