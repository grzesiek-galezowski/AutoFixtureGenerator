package autofixture.generators.objects.implementationdetails;

import com.google.common.base.Optional;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class InstanceCreation<T> {
  private final Invokable<T, T> rawValue;

  public InstanceCreation(Invokable<T, T> constructor1) {
    this.rawValue = constructor1;
  }

  public static <T> List<InstanceCreation<T>>
  getPrioritizedCreationMethods(
      TypeToken<T> typeToken) {
    final Constructor<?>[] constructors = getConstructorsOf(typeToken);
    //todo for factory methods Arrays.stream(typeToken.getRawType().getMethods()).filter(m -> m.retu)

    List<InstanceCreation<T>> instanceCreations =
        Arrays.stream(constructors)
            .map(typeToken::constructor)
            .map(InstanceCreation::new)
            .collect(toList());

    instanceCreations.sort((arg0, arg1) -> arg0.compareConstructors(arg1));
    return instanceCreations;
  }

  private static <T> Constructor<?>[] getConstructorsOf(final TypeToken<T> typeToken) {
    final Constructor<?>[] constructors = typeToken.getRawType().getDeclaredConstructors();
    Arrays.stream(constructors).forEach(c -> c.setAccessible(true));
    return constructors;
  }

  public static <T> Optional<InstanceCreation<T>> findBestOf(
      List<InstanceCreation<T>> constructors,
      ConstructorVisibility visibility) {
    int currentArgumentCount = Integer.MAX_VALUE;
    Optional<InstanceCreation<T>> currentConstructor = Optional.absent();

    for (final InstanceCreation<T> constructor : constructors) {
      if (visibility.appliesTo(constructor)
          && constructor.hasLessParametersThan(currentArgumentCount)
          && (!currentConstructor.isPresent() || !constructor.isParameterless())) {

        currentArgumentCount = constructor.getParametersCount();
        currentConstructor = Optional.of(constructor);
      }
    }
    return currentConstructor;
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

  public int compareConstructors(InstanceCreation instanceCreation2) {
    return Integer.compare(
        instanceCreation2.getParametersCount(), getParametersCount());
  }

  public Invokable<T, T> getRawValue() {
    return rawValue;
  }
}
