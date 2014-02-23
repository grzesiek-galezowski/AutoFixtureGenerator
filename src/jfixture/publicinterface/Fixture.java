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
import jfixture.publicinterface.generators.GeneratorsFactory;
import jfixture.publicinterface.generators.GeneratorsPipeline;
import jfixture.publicinterface.generators.InstanceGenerator;
import jfixture.publicinterface.generators.IntGenerator;
import jfixture.publicinterface.generators.PlainObjectGenerator;
import jfixture.publicinterface.generators.StringGenerator;

import com.google.common.reflect.TypeToken;

public class Fixture {

	private GeneratorsFactory generatorsFactory = new GeneratorsFactory();

	GeneratorsPipeline instanceGenerators = generatorsFactory.createBuiltinGenerators();

	public <T> T create(Class<T> clazz) {
		return this.create(TypeToken.of(clazz));
	}

	public <T> T create(TypeToken<T> typeToken) {
		return create(new ConcreteInstanceType<T>(typeToken));
	}
	
	
	public void register(InstanceGenerator instanceGenerator) {
		instanceGenerators.registerCustomization(instanceGenerator);
	}

	public void clearCustomizations() {
		instanceGenerators.clearCustomizations();
	}

	public <T> T create(InstanceType<T> instanceType) {
		return instanceGenerators.executeFor(instanceType, this);
	}

	
}
