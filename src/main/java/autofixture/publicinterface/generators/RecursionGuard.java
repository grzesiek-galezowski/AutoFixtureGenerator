package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceType;

import java.util.HashMap;

public class RecursionGuard {
    private HashMap<InstanceType<?>, Integer> recursionCounters = new HashMap<>();
    private int maxRecursionDepth;

    public RecursionGuard(int maxRecursionDepth) {
        this.maxRecursionDepth = maxRecursionDepth;
    }

    public <T> void addDepthLevelTo(InstanceType<T> instanceType) {
        initializeCounterFor(instanceType);
        incrementCounterFor(instanceType);
    }

    public <T> T defaultValueIfMaxDepthReachedOrGenerateUsing(
            GeneratorsPipeline generatorsPipeline,
            InstanceType<T> instanceType,
            FixtureContract fixture) {
        if(maxRecursionDepthIsReachedFor(instanceType)) {
            return null;
        } else {
            return generatorsPipeline.executeFor(instanceType, fixture);
        }
    }

    public <T> void removeDepthLevelFor(InstanceType<T> instanceType) {
        decrementCounterFor(instanceType);
    }

    private <T> void decrementCounterFor(InstanceType<T> instanceType) {
        Integer counter = recursionCounters.get(instanceType);
        counter--;
        recursionCounters.put(instanceType, counter);
    }

    private <T> void incrementCounterFor(InstanceType<T> instanceType) {
        Integer counter = recursionCounters.get(instanceType);
        counter++;
        recursionCounters.put(instanceType, counter);
    }

    private <T> boolean maxRecursionDepthIsReachedFor(InstanceType<T> instanceType) {
        return recursionCounters.get(instanceType) > maxRecursionDepth;
    }

    private <T> void initializeCounterFor(InstanceType<T> instanceType) {
        if(!recursionCounters.containsKey(instanceType)) {
            recursionCounters.put(instanceType, 0);
        }
    }

    public void setMaxDepth(int maxDepth) {
        this.maxRecursionDepth = maxDepth;
    }

}
