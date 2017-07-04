package autofixture.implementationdetails;

/**
 * Created by grzes on 04.07.2017.
 */
public class PackagePrivateVisibility<T> implements ConstructorVisibility<T> {
  @Override
  public boolean appliesTo(ConstructorCall<T> constructor) {
    return constructor.isPackagePrivate();
  }
}
