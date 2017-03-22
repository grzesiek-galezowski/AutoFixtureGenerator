package autofixture.implementationdetails;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.RecursionGuard;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class MapBasedRecursionGuard implements RecursionGuard {
  private final Map<InstanceType<?>, Integer> recursionCounters = new HashMap<>();
  private final GeneratorsPipeline recursionLimitReachedGeneratorsPipeline;
  private int maxRecursionDepth;

  public MapBasedRecursionGuard(
          final int maxRecursionDepth,
          final GeneratorsPipeline recursionLimitReachedGeneratorsPipeline) {
    this.maxRecursionDepth = maxRecursionDepth;
    this.recursionLimitReachedGeneratorsPipeline = recursionLimitReachedGeneratorsPipeline;
  }

  @Override
  public <T> void addDepthLevelTo(final InstanceType<T> instanceType) {
    initializeCounterFor(instanceType);
    incrementCounterFor(instanceType);
  }

  @Override
  @Nullable
  public <T> T defaultValueIfMaxDepthReachedOrGenerateUsing(
          final GeneratorsPipeline generatorsPipeline,
          final InstanceType<T> instanceType,
          final FixtureContract fixture) {
    if (maxRecursionDepthIsReachedFor(instanceType)) {
      return recursionLimitReachedGeneratorsPipeline.executeFor(instanceType, fixture);
    } else {
      return generatorsPipeline.executeFor(instanceType, fixture);
    }
  }

  @Override
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

  @Override
  public void setMaxDepth(final int maxDepth) {
    this.maxRecursionDepth = maxDepth;
  }

}
