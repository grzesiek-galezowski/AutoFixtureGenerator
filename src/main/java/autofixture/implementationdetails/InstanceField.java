package autofixture.implementationdetails;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceType;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Field;

public class InstanceField<T> {

  private Field field;
  private InstanceType<T> instanceType;
  private T instance;

  public InstanceField(Field field, InstanceType<T> instanceType, T instance) {
    this.field = field;
    this.instanceType = instanceType;
    this.instance = instance;
  }

  public TypeToken<?> ResolveActualType() {
    return instanceType.ResolveActualTypeOf(field);
  }

  public void setValueUsing(FixtureContract fixture) throws IllegalAccessException {
    field.set(instance, fixture.create(ResolveActualType()));

  }
}
