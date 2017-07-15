package autofixture.generators.objects.implementationdetails;

/**
 * Created by grzes on 04.07.2017.
 */
public interface ConstructorVisibility<T> {
  boolean appliesTo(ConstructorCall<T> constructor);

  static <U> ConstructorVisibility<U> getPublic() {
    return new PublicVisibility<U>();
  }

  static <U> ConstructorVisibility<U> getPackagePrivate() {
    return new PackagePrivateVisibility<U>();
  }

}
