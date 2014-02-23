package jfixture.publicinterface;

import java.util.Arrays;
import java.util.LinkedList;

import jfixture.publicinterface.generators.ArrayGenerator;
import jfixture.publicinterface.generators.BigDecimalGenerator;
import jfixture.publicinterface.generators.BigIntGenerator;
import jfixture.publicinterface.generators.BooleanGenerator;
import jfixture.publicinterface.generators.BuiltInCollectionGenerator;
import jfixture.publicinterface.generators.ByteAndCharGenerator;
import jfixture.publicinterface.generators.CalendarGenerator;
import jfixture.publicinterface.generators.ConcreteObjectGenerator;
import jfixture.publicinterface.generators.DateGenerator;
import jfixture.publicinterface.generators.DoubleGenerator;
import jfixture.publicinterface.generators.EnumGenerator;
import jfixture.publicinterface.generators.InstanceGenerator;
import jfixture.publicinterface.generators.IntGenerator;
import jfixture.publicinterface.generators.PlainObjectGenerator;
import jfixture.publicinterface.generators.StringGenerator;

import com.google.common.reflect.TypeToken;

public class Fixture {

	private LinkedList<InstanceGenerator> instanceGenerators = new LinkedList<InstanceGenerator>(
			Arrays.asList(new InstanceGenerator[] {
			new EnumGenerator(),
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
			new ArrayGenerator(),
			new BuiltInCollectionGenerator(),
			new ConcreteObjectGenerator()
	}));
	private int customizationsCount = 0; 

	public <T> T create(Class<T> clazz) {
		return this.create(TypeToken.of(clazz));
	}

	public <T> T create(TypeToken<T> typeToken) {
		for(InstanceGenerator generator : instanceGenerators) {
			if(generator.AppliesTo(typeToken)) {

				return (T)generator.next(typeToken, this);
			}
		}
		
		throw new ObjectCreationException(typeToken);
	}
	
	
	public void register(InstanceGenerator instanceGenerator) {
		instanceGenerators.add(0, instanceGenerator);
		customizationsCount++;
	}

	public void clearCustomizations() {
		for(int i = 0 ; i < customizationsCount ; ++i) {
			instanceGenerators.remove(0);
		}
		customizationsCount = 0;
	}

	
}
