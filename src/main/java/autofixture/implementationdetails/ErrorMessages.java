package autofixture.implementationdetails;

public class ErrorMessages {
  public static String msg(final String methodName) {
    return "generic types are not allowed for this method. " +
        "Try Any." + methodName + "(new InstanceOf<MyType<MyGenericType>>() {}).";
  }

  public static String msgInline(final String methodName) {
    return "generic types are not allowed for this method. " +
        "Try Any." + methodName + "(new InstanceOf<MyType<MyGenericType>>() {}, generator).";
  }
}
