package autofixture.generators.objects;

import autofixture.exceptions.ObjectCreationException;
import autofixture.interfaces.InstanceGenerator;
import autofixture.generators.objects.implementationdetails.Debug;
import autofixture.interfaces.*;
import com.google.common.base.Optional;

import java.util.List;

public class ConcreteObjectGenerator implements InstanceGenerator {

  private boolean omittingAutoProperties = false;

  @Override
  public <T> boolean appliesTo(final InstanceType<T> typeToken) {
    return true;
  }

  @Override
  public <T> T next(final InstanceType<T> type, final FixtureContract fixture) {
    final T instance = createInstanceOf(type, fixture);
    try {
      if (!omittingAutoProperties) {
        makeBestEffortAttemptToInvokeAllSettersOn(instance, type, fixture);
        makeBestEffortAttemptToSetAllPublicFields(instance, type, fixture);
      }

    } catch (final IllegalAccessException e) {
      throw new ObjectCreationException(type, e);
    }
    return instance;
  }

  private <T> void makeBestEffortAttemptToSetAllPublicFields(final T instance,
                                                             final InstanceType<T> type, final FixtureContract fixture) throws IllegalAccessException {
    final List<InstanceField<T>> publicFields = type.getAllPublicFieldsOf(instance);
    for (final InstanceField<T> publicField : publicFields) {
      publicField.setValueUsing(fixture);
    }
  }

  private <T> T createInstanceOf(final InstanceType<T> type, final FixtureContract fixture) {
    final Call<T, T> currentConstructor = type.findSuitableConstructorWithLeastParameters();
    final T instance = currentConstructor.invokeWithArgumentsCreatedUsing(fixture);
    return instance;
  }

  private <T> void makeBestEffortAttemptToInvokeAllSettersOn(final T instance,
                                                             final InstanceType<T> type, final FixtureContract fixture) {
    final List<Call<T, Object>> setters = type.getAllSetters();
    for (final Call<T, Object> setter : setters) {
      makeBestEffortAttemptToInvoke(setter, instance, fixture);
    }
  }

  private <T> void makeBestEffortAttemptToInvoke(final Call<T, Object> setter,
                                                 final T instance, final FixtureContract fixture) {
    try {
      setter.invokeWithArgumentsCreatedUsing(fixture, Optional.of(instance));
    } catch (final Exception t) {
      Debug.debugPrint(t);
    }
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    this.omittingAutoProperties = isOn;

  }

}
