package autofixture.specification.acceptance.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasArrayUniqueItemsMatcher<T> extends TypeSafeMatcher<T[]> {

  @Override
  public void describeTo(Description description) {
    description.appendText("unique items");

  }

  @Override
  protected boolean matchesSafely(T[] items) {
    for (int i = 0; i < items.length; ++i) {
      for (int m = 0; m < items.length; ++m) {
        if (i != m && items[i] == items[m]) {
          return false;
        }
      }
    }
    return true;
  }

}
