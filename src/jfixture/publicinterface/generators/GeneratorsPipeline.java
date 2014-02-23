package jfixture.publicinterface.generators;

import java.util.LinkedList;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.ObjectCreationException;

public class GeneratorsPipeline {

	private int customizationsCount = 0;
	private final LinkedList<InstanceGenerator> generators;

	public GeneratorsPipeline(LinkedList<InstanceGenerator> generators) {
		this.generators = generators;
	}

	public <T> T executeFor(TypeToken<T> typeToken, Fixture fixture) {
		for(InstanceGenerator generator : generators) {
			if(generator.AppliesTo(typeToken)) {
				return (T)generator.next(typeToken, fixture);
			}
		}
		
		throw new ObjectCreationException(typeToken);

	}

	public void registerCustomization(InstanceGenerator instanceGenerator) {
		generators.add(0, instanceGenerator);
		customizationsCount++;		
	}

	public void clearCustomizations() {
		for(int i = 0 ; i < customizationsCount ; ++i) {
			generators.remove(0);
		}
		customizationsCount = 0;
	}
	
}
