package autofixture.generators.objects.implementationdetails;

import java.util.function.Function;

public class VisibilityCondition<T> implements ConstructorVisibility<T> {

  private final Function<InstanceCreation<T>, Boolean> condition;

  public VisibilityCondition(final Function<InstanceCreation<T>, Boolean> visibilityCondition) {
    condition = visibilityCondition;
  }

  @Override
  public boolean appliesTo(InstanceCreation<T> constructor) {
    return condition.apply(constructor);
  }
}
