package autofixture.implementationdetails;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConstructorCall<T> {
  private final Invokable<T, T> rawValue;

  public ConstructorCall(Invokable<T, T> constructor1) {
    this.rawValue = constructor1;
  }

  public static <T> List<ConstructorCall<T>>
  getConstructorsSortedFromLongestToShortestParametersCount(
      TypeToken<T> typeToken) {
    final Constructor<?>[] constructors = typeToken.getRawType().getDeclaredConstructors();
    Arrays.stream(constructors).forEach(c -> c.setAccessible(true));

    List<ConstructorCall<T>> constructorCalls =
        Arrays.stream(constructors)
            .map(typeToken::constructor)
            .map(ConstructorCall::new)
            .collect(toList());

    constructorCalls.sort((arg0, arg1) -> arg0.compareConstructors(arg1));
    return constructorCalls;
  }

  boolean isParameterless() {
    return getParametersCount() == 0;
  }

  boolean hasLessParametersThan(int currentArgumentCount) {
    return getParametersCount() < currentArgumentCount;
  }

  int getParametersCount() {
    return getRawValue().getParameters().size();
  }

  public boolean isPackagePrivate() {
    return getRawValue().isPackagePrivate();
  }

  public boolean isPublic() {
    return getRawValue().isPublic();
  }

  public int compareConstructors(ConstructorCall constructorCall2) {
    return Integer.compare(
        constructorCall2.getParametersCount(), getParametersCount());
  }

  public Invokable<T, T> getRawValue() {
    return rawValue;
  }
}
