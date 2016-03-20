package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

import java.util.List;

public class DefaultGeneratorsPipeline implements GeneratorsPipeline {

  private final List<InstanceGenerator> generators;
  private int customizationsCount = 0;
  private boolean autoPropertiesDisabled = false;

  public DefaultGeneratorsPipeline(final List<InstanceGenerator> generators) {
    this.generators = generators;
  }

  public <T> T executeFor(final InstanceType<T> instanceType, final FixtureContract fixture) {
    for (final InstanceGenerator generator : generators) {
      if (generator.appliesTo(instanceType)) {
        return generator.next(instanceType, fixture);
      }
    }
    throw new ObjectCreationException(instanceType);
  }

  public void registerCustomization(final InstanceGenerator instanceGenerator) {
    generators.add(0, instanceGenerator);
    instanceGenerator.setOmittingAutoProperties(this.autoPropertiesDisabled);
    customizationsCount++;
  }

  public void clearCustomizations() {
    for (int i = 0; i < customizationsCount; ++i) {
      generators.remove(0);
    }
    customizationsCount = 0;
  }

  public void setOmittingAutoProperties(final boolean isOn) {
    autoPropertiesDisabled = isOn;
    for (final InstanceGenerator generator : generators) {
      generator.setOmittingAutoProperties(isOn);
    }
  }
}
