package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;

import java.util.Collection;

public class VavrIteratorGenerator implements InstanceGenerator {

  //apparently, when the lists are not cached,
  //the size of the iterator becomes 0 pretty soon
  List<List> iteratorParents = List.empty();

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.Iterator.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    List<?> parent = List.ofAll(instanceType.turnIntoCollection(fixture));
    iteratorParents = iteratorParents.append(parent);
    Iterator<?> iterator = parent.iterator();
    return (T) iterator;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

