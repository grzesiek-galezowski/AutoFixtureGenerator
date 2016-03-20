package autofixture.implementationdetails;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceType;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;

public class InstanceField<T> {

  private final Field field;
  private final InstanceType<T> instanceType;
  private final T instance;

  public InstanceField(final Field field, final InstanceType<T> instanceType, final T instance) {
    this.field = field;
    this.instanceType = instanceType;
    this.instance = instance;
  }

  public TypeToken<?> resolveActualType() {
    return instanceType.resolveActualTypeOf(field);
  }

  public void setValueUsing(final FixtureContract fixture) throws IllegalAccessException {
    field.set(instance, fixture.create(resolveActualType()));

  }
}
