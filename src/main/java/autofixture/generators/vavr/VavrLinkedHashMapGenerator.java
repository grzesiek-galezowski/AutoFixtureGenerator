package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.LinkedHashMap;

import java.util.stream.Stream;

public class VavrLinkedHashMapGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(
        io.vavr.collection.LinkedHashMap.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {

    Stream<? extends Tuple2<?, ?>> stream = Stream.generate(() -> Tuple.of(
        fixture.create(instanceType.getNestedGenericType1()),
        fixture.create(instanceType.getNestedGenericType2())))
        .limit(fixture.getRepeatCount());
    LinkedHashMap<?, ?> hashMap = LinkedHashMap.ofAll(stream, t -> t._1, t -> t._2);

    return (T) hashMap;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
