package autofixture.specification.acceptance.testfixtures;

public class GenericDataStructure<T> {
  public T z;
  private T y;

  public T getY() {
    return y;
  }

  public void setY(T newY) {
    y = newY;
  }
  //public int x;
}
