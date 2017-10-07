package autofixture.specification.acceptance;

import static junit.framework.TestCase.fail;

public class CustomAssertions {
  public static <T> T assertThrows(Class<T> exceptionClass, Runnable func) {
    try {
      func.run();
      fail("Expected " + exceptionClass + " being thrown, but got nothing");
      throw new RuntimeException("to make compiler happy");
    } catch (Throwable e) {
      if(e.getClass() != exceptionClass) {
        fail("Expected " + exceptionClass + " being thrown, but got " + e.getClass());
      }
      return (T)e;
    }
  }
}
