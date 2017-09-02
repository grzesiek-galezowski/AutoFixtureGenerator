package autofixture.generators.objects.implementationdetails;

/**
 * Created by grzes on 04.07.2017.
 */
public interface ConstructorVisibility<T> {
  boolean appliesTo(InstanceCreation<T> constructor);

  static <U> ConstructorVisibility<U> getPublic() {
    return new VisibilityCondition<>(c -> c.isPublic());
  }

  static <U> ConstructorVisibility<U> getPackagePrivate() {
    return new VisibilityCondition<>(c -> c.isPackagePrivate());
  }

}
