package autofixture.generators.composition;

import autofixture.generators.vavr.*;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.GeneratorsPipeline;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;
import io.vavr.collection.*;

public class VavrGenerator implements InstanceGenerator {
  private Supplier<GeneratorsPipeline> pipeline = Suppliers.memoize(() ->
      new DefaultGeneratorsPipeline(
        Lists.newArrayList(
          new VavrCollectionGenerator<>(
              List.class,
              List::ofAll),
          new VavrCollectionGenerator<>(
              Array.class,
              Array::ofAll),
          new VavrMapGenerator<>(
              HashMap.class,
              HashMap::ofAll),
          new VavrCollectionGenerator<>(
              HashSet.class,
              HashSet::ofAll),
          new VavrCollectionGenerator<>(
              TreeSet.class,
              x -> TreeSet.ofAll((Iterable)x)),
          new VavrMapGenerator<>(
              TreeMap.class,
              TreeMap::ofAll),
          new VavrNodeGenerator(),
          new VavrMapGenerator<>(
              LinkedHashMap.class,
              LinkedHashMap::ofAll),
          new VavrCollectionGenerator<>(
              LinkedHashSet.class,
              LinkedHashSet::ofAll),
          new VavrCollectionGenerator<>(
              Vector.class,
              Vector::ofAll),
          new VavrCollectionGenerator<>(
              PriorityQueue.class,
              x -> PriorityQueue.ofAll((Iterable)x)),
          new VavrCollectionGenerator<>(
              Queue.class,
              Queue::ofAll),
          new VavrCharSeqGenerator(),
          new VavrMapGenerator<>(
              TreeMultimap.class,
              TreeMultimap.withSeq()::ofAll),
          new VavrMapGenerator<>(
              HashMultimap.class,
              HashMultimap.withSeq()::ofAll),
          new VavrMapGenerator<>(
              LinkedHashMultimap.class,
              LinkedHashMultimap.withSeq()::ofAll),
          new VavrIteratorGenerator(),
          new VavrOptionGenerator(),
          new VavrEitherGenerator(),
          new VavrValidationGenerator()
      )));


  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isFromPackage("io.vavr");
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {

    return pipeline.get().executeFor(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    //not needed for now
  }
}
