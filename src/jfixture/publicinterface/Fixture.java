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
			new ByteAndCharGenerator(),
			new BooleanGenerator(),
	};
	
	private CollectionGenerator[] collectionGenerators = new CollectionGenerator[] {
      //TODO add collection generators in here
	};
	
  public <T> T create(Class<T> clazz) {
	  for(ObjectGenerator generator : generators) {
		  if(generator.AppliesTo(clazz)) {
			  return (T)generator.next();
		  }
	  }
	  
	  for(CollectionGenerator generator : collectionGenerators) {
		  if(generator.AppliesTo(clazz)) {
			  return (T)generator.next(clazz, this);
		  }
	  }
	  
	  throw new ObjectCreationException(clazz);
  }
}
