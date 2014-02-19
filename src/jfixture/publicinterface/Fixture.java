package jfixture.publicinterface;
import java.lang.Class;

public class Fixture {
	private IntGenerator intGenerator = new IntGenerator();
	private StringGenerator stringGenerator = new StringGenerator();
	private DoubleGenerator doubleGenerator = new DoubleGenerator();
	
  public <T> T create(Class<T> clazz) {
	  if(clazz == int.class) {
		  return (T)intGenerator.next();
	  }
	  else if(clazz == String.class) {
		  return (T)stringGenerator.next();
	  }
	  else if(clazz == Double.class) {
		  return (T)doubleGenerator.next();
	  }
	  
	  //todo throw exception if type cannot be generated
	  return null;
  }
}
