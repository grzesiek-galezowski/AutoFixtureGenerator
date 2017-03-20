package autofixture.implementationdetails;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceType;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class RecursionGuard {
  private final Map<InstanceType<?>, Integer> recursionCounters = new HashMap<>();
  private int maxRecursionDepth;

  public RecursionGuard(final int maxRecursionDepth) {
    this.maxRecursionDepth = maxRecursionDepth;
  }

  public <T> void addDepthLevelTo(final InstanceType<T> instanceType) {
    initializeCounterFor(instanceType);
    incrementCounterFor(instanceType);
  }

  @Nullable
  public <T> T defaultValueIfMaxDepthReachedOrGenerateUsing(
      final GeneratorsPipeline generatorsPipeline,
      final InstanceType<T> instanceType,
      final FixtureContract fixture) {
    if (maxRecursionDepthIsReachedFor(instanceType)) {
      return null;
    } else {
      return generatorsPipeline.executeFor(instanceType, fixture);
    }
  }

  public <T> void removeDepthLevelFor(final InstanceType<T> instanceType) {
    decrementCounterFor(instanceType);
  }

  private <T> void decrementCounterFor(final InstanceType<T> instanceType) {
    Integer counter = recursionCounters.get(instanceType);
    counter--;
    recursionCounters.put(instanceType, counter);
  }

  private <T> void incrementCounterFor(final InstanceType<T> instanceType) {
    Integer counter = recursionCounters.get(instanceType);
    counter++;
    recursionCounters.put(instanceType, counter);
  }

  private <T> boolean maxRecursionDepthIsReachedFor(final InstanceType<T> instanceType) {
    initializeCounterFor(instanceType);
    return recursionCounters.get(instanceType) > maxRecursionDepth;
  }

  private <T> void initializeCounterFor(final InstanceType<T> instanceType) {
    if (!recursionCounters.containsKey(instanceType)) {
      recursionCounters.put(instanceType, 0);
    }
  }

  public void setMaxDepth(final int maxDepth) {
    this.maxRecursionDepth = maxDepth;
  }

}
