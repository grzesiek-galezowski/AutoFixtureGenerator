package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import io.vavr.collection.List;
import io.vavr.collection.Tree;

public class VavrNodeGenerator implements InstanceGenerator {
  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(Tree.Node.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T)new Tree.Node(
        fixture.create(instanceType.getNestedGenericType1()),
            List.empty()
            );
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
