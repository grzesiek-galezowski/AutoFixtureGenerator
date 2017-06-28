package autofixture.specification.acceptance.testfixtures;

/**
 * Created by grzes on 28.06.2017.
 */
public class StructureWithCopyConstructor {
  private int x;
  private int y;

  public StructureWithCopyConstructor(StructureWithCopyConstructor s) {
    this.setX(s.getX());
    this.setY(s.getY());
  }

  public int getX() {
    return x;
  }

  private void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  private void setY(int y) {
    this.y = y;
  }
}
