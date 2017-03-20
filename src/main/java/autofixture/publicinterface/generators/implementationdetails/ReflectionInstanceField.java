package autofixture.publicinterface.generators.implementationdetails;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceField;
import autofixture.interfaces.InstanceType;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;

public class ReflectionInstanceField<T> implements InstanceField<T> {

  private final Field field;
  private final InstanceType<T> instanceType;
  private final T instance;

  public ReflectionInstanceField(final Field field, final InstanceType<T> instanceType, final T instance) {
    this.field = field;
    this.instanceType = instanceType;
    this.instance = instance;
  }

  @Override
  public TypeToken<?> resolveActualType() {
    return instanceType.resolveActualTypeOf(field);
  }

  @Override
  public void setValueUsing(final FixtureContract fixture) throws IllegalAccessException {
    field.set(instance, fixture.create(resolveActualType()));

  }
}
