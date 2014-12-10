package autofixture.specification.acceptance.testfixtures;

public class GenericObject<T> {
  private T instance;

  public GenericObject(T instance) {
    this.instance = instance;

  }

  public T getInstance() {
    return instance;
  }
}
