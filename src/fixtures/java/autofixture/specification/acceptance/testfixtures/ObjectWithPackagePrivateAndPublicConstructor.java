package autofixture.specification.acceptance.testfixtures;

/**
 * Created by grzes on 04.07.2017.
 */
public class ObjectWithPackagePrivateAndPublicConstructor {
  private String str = "Hello";
  private int x;

  public ObjectWithPackagePrivateAndPublicConstructor(int x) {
    this.x = x;
  }

  ObjectWithPackagePrivateAndPublicConstructor(int x, String str) {
    this.x = x;
    this.str = str;
  }

  public String getStr() {
    return str;
  }
}
