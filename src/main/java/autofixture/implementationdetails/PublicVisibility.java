package autofixture.implementationdetails;

public class PublicVisibility<T> implements ConstructorVisibility<T> {

  @Override
  public boolean appliesTo(ConstructorCall<T> constructor) {
    return constructor.isPublic();
  }
}
