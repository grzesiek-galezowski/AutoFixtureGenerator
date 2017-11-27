package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import io.vavr.collection.TreeSet;

import java.util.Collection;
import java.util.TreeMap;
import java.util.stream.Stream;

public class VavrTreeSetGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(
        io.vavr.collection.TreeSet.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {

    return (T)TreeSet.ofAll((Iterable)fixture.createMany(instanceType.getNestedGenericType1()));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }

}
