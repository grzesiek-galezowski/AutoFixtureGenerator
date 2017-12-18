package autofixture.generators.composition;

import autofixture.exceptions.ObjectCreationException;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.util.List;

public class DefaultGeneratorsPipeline implements GeneratorsPipeline {

  private final List<InstanceGenerator> generators;
  private int customizationsCount = 0;
  private boolean autoPropertiesDisabled = false;

  public DefaultGeneratorsPipeline(final List<InstanceGenerator> generators) {
    this.generators = generators;
  }

  @Override
  public <T> T generateInstanceOf(final InstanceType<T> instanceType, final FixtureContract fixture) {
    for (final InstanceGenerator generator : generators) {
      if (generator.appliesTo(instanceType)) {
        return generator.next(instanceType, fixture);
      }
    }
    throw new ObjectCreationException(instanceType);
  }

  @Override
  public <T> T generateEmptyInstanceOf(final InstanceType<T> instanceType, final FixtureContract fixture) {

    //todo some dupl. between here and generateFor...
    for (final InstanceGenerator generator : generators) {
      if (generator.appliesTo(instanceType)) {
        return generator.nextEmpty(instanceType, fixture);
      }
    }
    throw new ObjectCreationException(instanceType);
  }

  @Override
  public void registerCustomization(final InstanceGenerator instanceGenerator) {
    generators.add(0, instanceGenerator);
    instanceGenerator.setOmittingAutoProperties(this.autoPropertiesDisabled);
    customizationsCount++;
  }

  @Override
  public void clearCustomizations() {
    for (int i = 0; i < customizationsCount; ++i) {
      generators.remove(0);
    }
    customizationsCount = 0;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    autoPropertiesDisabled = isOn;
    for (final InstanceGenerator generator : generators) {
      generator.setOmittingAutoProperties(isOn);
    }
  }
}
