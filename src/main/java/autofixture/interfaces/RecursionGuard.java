package autofixture.interfaces;

import javax.annotation.Nullable;

/**
 * Created by grzes on 20.03.2017.
 */
public interface RecursionGuard {
    <T> void addDepthLevelTo(InstanceType<T> instanceType);

    @Nullable
    <T> T defaultValueIfMaxDepthReachedOrGenerateUsing(
            GeneratorsPipeline generatorsPipeline,
            InstanceType<T> instanceType,
            FixtureContract fixture);

    <T> void removeDepthLevelFor(InstanceType<T> instanceType);

    void setMaxDepth(int maxDepth);
}
