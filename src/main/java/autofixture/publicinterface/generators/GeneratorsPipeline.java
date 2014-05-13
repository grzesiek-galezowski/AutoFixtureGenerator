package autofixture.publicinterface.generators;

import java.util.LinkedList;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

public class GeneratorsPipeline {

	private int customizationsCount = 0;
	private final LinkedList<InstanceGenerator> generators;
	private boolean autoPropertiesDisabled = false;

	public GeneratorsPipeline(LinkedList<InstanceGenerator> generators) {
		this.generators = generators;
	}

	public <T> T executeFor(InstanceType<T> instanceType, FixtureContract fixture) {
		for(InstanceGenerator generator : generators) {
			if(generator.appliesTo(instanceType)) {
				return (T)generator.next(instanceType, fixture);
			}
		}
		
		throw new ObjectCreationException(instanceType);

	}

	public void registerCustomization(InstanceGenerator instanceGenerator) {
		generators.add(0, instanceGenerator);
		instanceGenerator.setOmittingAutoProperties(this.autoPropertiesDisabled);
		customizationsCount++;		
	}

	public void clearCustomizations() {
		for(int i = 0 ; i < customizationsCount ; ++i) {
			generators.remove(0);
		}
		customizationsCount = 0;
	}

	public void setOmittingAutoProperties(boolean isOn) {
		autoPropertiesDisabled = isOn;
		for(InstanceGenerator generator : generators) {
			generator.setOmittingAutoProperties(isOn);
		}
		
	}
	
}
