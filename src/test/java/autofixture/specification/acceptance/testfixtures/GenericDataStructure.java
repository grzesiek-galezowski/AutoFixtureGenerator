package autofixture.specification.acceptance.testfixtures;

public class GenericDataStructure<T> 
{
  private T y;
  
  public T getY() {
	  return y;
  }
  
  public void setY(T newY) {
	  y = newY;
  }
  
  public T z;
  //public int x;
}
