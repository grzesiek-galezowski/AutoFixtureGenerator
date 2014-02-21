package jfixture.publicinterface;
import java.lang.Class;
import java.util.Collection;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.generators.*;

public class Fixture {
	private PrimitiveGenerator[] generators = new PrimitiveGenerator[] {
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

	private CompoundObjectGenerator[] collectionGenerators = new CompoundObjectGenerator[] {
			new ArrayGenerator(),
			new BuiltInCollectionGenerator(),
	};

	private EnumGenerator enumGenerator = new EnumGenerator();
	private ConcreteObjectGenerator objectGenerator = new ConcreteObjectGenerator();


	@SuppressWarnings("unchecked")
	public <T> T create(TypeToken<T> typeToken) {
		try {
			for(PrimitiveGenerator generator : generators) {
				if(generator.AppliesTo(typeToken)) {
					return (T)generator.next();
				}
			}

			if(enumGenerator.AppliesTo(typeToken)) {
				return (T)enumGenerator.next(typeToken);
			}

			for(CompoundObjectGenerator generator : collectionGenerators) {
				if(generator.AppliesTo(typeToken)) {

					return (T)generator.next(typeToken, this);
				}
			}

			return (T) objectGenerator.next(typeToken, this);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ObjectCreationException(typeToken, e);
		}

		//throw new ObjectCreationException(typeToken);
	}

}
