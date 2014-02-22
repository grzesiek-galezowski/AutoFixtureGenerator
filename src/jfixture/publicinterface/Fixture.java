package jfixture.publicinterface;

import java.lang.Class;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.generators.*;

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

	@SuppressWarnings("unchecked")
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
