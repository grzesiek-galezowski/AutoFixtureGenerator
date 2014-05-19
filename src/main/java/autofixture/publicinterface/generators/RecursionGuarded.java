package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class RecursionGuarded implements GeneratorsPipeline {
    private final GeneratorsPipeline generatorsPipeline;
    private RecursionGuard recursionGuard;

    public RecursionGuarded(GeneratorsPipeline generatorsPipeline, RecursionGuard recursionGuard) {
        this.generatorsPipeline = generatorsPipeline;
        this.recursionGuard = recursionGuard;
    }

    @Override
    public <T> T executeFor(InstanceType<T> instanceType, FixtureContract fixture) {
        try {
            recursionGuard.addDepthLevelTo(instanceType);
            return recursionGuard.defaultValueIfMaxDepthReachedOrGenerateUsing(generatorsPipeline, instanceType, fixture);
        } finally {
            recursionGuard.removeDepthLevelFor(instanceType);
        }

    }

    @Override
    public void registerCustomization(InstanceGenerator instanceGenerator) {
        generatorsPipeline.registerCustomization(instanceGenerator);
    }

    @Override
    public void clearCustomizations() {
        generatorsPipeline.clearCustomizations();
    }

    @Override
    public void setOmittingAutoProperties(boolean isOn) {
        generatorsPipeline.setOmittingAutoProperties(isOn);
    }
}
