package jfixture.publicinterface;
import java.lang.Class;
import jfixture.publicinterface.generators.*;

public class Fixture {
	private ObjectGenerator[] generators = new ObjectGenerator[] {
			new StringGenerator(),
			new IntGenerator(),
			new DoubleGenerator(),
			new BigIntGenerator(),
			new BigDecimalGenerator(),
			new DateGenerator(),
			new CalendarGenerator(),
			new PlainObjectGenerator(),
			new ByteGenerator(),
	};
	
  public <T> T create(Class<T> clazz) {
	  for(ObjectGenerator generator : generators) {
		  if(generator.AppliesTo(clazz)) {
			  return (T)generator.next();
		  }
	  }
	  
	  //todo throw exception if type cannot be generated
	  return null;
  }
}
