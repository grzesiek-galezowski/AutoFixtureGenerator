package autofixture.generators.objects;

import autofixture.exceptions.ObjectCreationException;
import autofixture.generators.objects.implementationdetails.Debug;
import autofixture.interfaces.*;
import com.google.common.base.Optional;
import org.objenesis.ObjenesisStd;

import java.util.List;

public class ConcreteObjectGenerator implements InstanceGenerator {

  private boolean omittingAutoProperties = false;

  private final ObjenesisStd objenesis = new ObjenesisStd(true);

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

  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return objenesis.newInstance((Class<T>)instanceType.getRawType());
  }

  private <T> void makeBestEffortAttemptToSetAllPublicFields(final T instance,
                                                             final InstanceType<T> type, final FixtureContract fixture) throws IllegalAccessException {
    final List<InstanceField<T>> publicFields = type.getAllPublicSettableFieldsOf(instance);
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
      Debug.print(t);
    }
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
    this.omittingAutoProperties = isOn;

  }

}
