package autofixture.interfaces;

/**
 * Created by grzes on 20.03.2017.
 */
public interface GeneratorsFactory {
    GeneratorsPipeline createBuiltinGenerators(RecursionGuard recursionGuard);

    java.util.List<InstanceGenerator> matchedInTheFollowingOrder(InstanceGenerator... ts);
}
