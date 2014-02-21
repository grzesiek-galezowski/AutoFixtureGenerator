package jfixture.publicinterface;
import java.lang.Class;
import java.util.Collection;

import com.google.common.reflect.TypeToken;

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
      new ArrayGenerator(),
      new BuiltInCollectionGenerator(),
	};
	
	private EnumGenerator enumGenerator = new EnumGenerator();
	
	public <T> T create(TypeToken<T> typeToken) {
		for(CollectionGenerator generator : collectionGenerators) {
			if(generator.AppliesTo(typeToken)) {
				try {
					return (T)generator.next(typeToken, this);
				} catch (InstantiationException | IllegalAccessException e) {
					throw new ObjectCreationException(typeToken, e);
				}
			}
		}
		
      for(ObjectGenerator generator : generators) {
		  if(generator.AppliesTo(typeToken)) {
			  return (T)generator.next();
		  }
	  }
	  
      if(enumGenerator.AppliesTo(typeToken)) {
    	  return (T)enumGenerator.next(typeToken);
      }
	  
	  throw new ObjectCreationException(typeToken);
  }
	
}
