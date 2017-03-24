package autofixture.specification.acceptance.matchers;

import org.hamcrest.Matcher;

import java.lang.reflect.Array;

/**
 * Created by grzes on 24.03.2017.
 */
public class ArrayMatchers {
  public static <T> T[] typeOf(Class<T> clazz) {
    return (T[]) Array.newInstance(clazz,0);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static <T> Matcher<? super T[]> hasUniqueItems() {
    return new HasArrayUniqueItemsMatcher();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static <T> Matcher<? super T[]> hasLength(int i) {
    return new HasArrayLengthMatcher(i);
  }
}
