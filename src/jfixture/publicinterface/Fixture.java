package jfixture.publicinterface;

import jfixture.publicinterface.generators.GeneratorsFactory;
import jfixture.publicinterface.generators.GeneratorsPipeline;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.generators.implementationdetails.ConcreteInstanceType;

import com.google.common.reflect.TypeToken;

public class Fixture implements FixtureContract {
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

	public <T> T createWith(InlineInstanceGenerator<T> inlineGenerator) {
		return inlineGenerator.next(this);
	}

}
