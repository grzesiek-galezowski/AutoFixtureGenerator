package autofixture.specification.acceptance.testfixtures;

/**
 * Created by grzes on 04.07.2017.
 */
public class ObjectWithPackagePrivateConstructor {

  private int x;

  ObjectWithPackagePrivateConstructor(int x) {

    this.x = x;
  }

  public int getX() {
    return x;
  }
}
