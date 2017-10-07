package autofixture.generators.objects.implementationdetails;

public class TypeAssertions {
  public static void assertIsNotParameterized(final Class<?> requestedType, final String message) {
    if(isParameterized(requestedType)) {
      throw new RuntimeException(message);
    }
  }

  //todo find a better place for this method, maybe add to InstanceType
  private static <T> boolean isParameterized(final Class<?> aClass) {
    return aClass.getTypeParameters().length > 0;
  }
}
