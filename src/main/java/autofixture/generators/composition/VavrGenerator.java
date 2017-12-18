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
          List::ofAll,
          List::empty),
        new VavrCollectionGenerator<>(
          Array.class,
          Array::ofAll,
          Array::empty),
        new VavrMapGenerator<>(
          HashMap.class,
          HashMap::ofAll,
          HashMap::empty),
        new VavrCollectionGenerator<>(
          HashSet.class,
          HashSet::ofAll,
          HashSet::empty),
        new VavrCollectionGenerator<>(
          TreeSet.class,
          x -> TreeSet.ofAll((Iterable) x),
          TreeSet::empty),
        new VavrMapGenerator<>(
          TreeMap.class,
          TreeMap::ofAll,
          TreeMap::empty),
        new VavrNodeGenerator(),
        new VavrMapGenerator<>(
          LinkedHashMap.class,
          LinkedHashMap::ofAll,
          LinkedHashMap::empty),
        new VavrCollectionGenerator<>(
          LinkedHashSet.class,
          LinkedHashSet::ofAll,
          LinkedHashSet::empty),
        new VavrCollectionGenerator<>(
          Vector.class,
          Vector::ofAll,
          Vector::empty),
        new VavrCollectionGenerator<>(
          PriorityQueue.class,
          x -> PriorityQueue.ofAll((Iterable) x),
          PriorityQueue::empty),
        new VavrCollectionGenerator<>(
          Queue.class,
          Queue::ofAll,
          Queue::empty),
        new VavrCharSeqGenerator(),
        new VavrMapGenerator<>(
          TreeMultimap.class,
          TreeMultimap.withSeq()::ofAll,
          TreeMultimap.withSeq()::empty),
        new VavrMapGenerator<>(
          HashMultimap.class,
          HashMultimap.withSeq()::ofAll,
          HashMultimap.withSeq()::empty),
        new VavrMapGenerator<>(
          LinkedHashMultimap.class,
          LinkedHashMultimap.withSeq()::ofAll,
          LinkedHashMultimap.withSeq()::empty),
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
    return pipeline.get().generateInstanceOf(instanceType, fixture);
  }

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return pipeline.get().generateEmptyInstanceOf(instanceType, fixture);
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    //not needed for now
  }
}
