package autofixture.interfaces;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface InstanceType<T> {
  Collection<?> turnIntoCollection(FixtureContract fixture);

  Map turnIntoMapInstance(FixtureContract fixture);

  default boolean isMap() {
    return isAssignableTo(Map.class);
  }

  InstanceType<?> getArrayElementType();

  <TAssignable> boolean isAssignableFrom(
    Class<TAssignable> clazz);

  Class<? super T> getRawType();

  TypeToken<? super T> getToken();

  Type getType();

  Type getWrapper();

  boolean isArray();

  Invokable<T, T> constructor(Constructor<?> constructor);

  Object createArray(Object[] objects);

  Object createEmptyArray();

  boolean isAssignableTo(Class<?> clazz);

  boolean isRawTypeAssignableFrom(Class<?> clazz);

  @SuppressWarnings("rawtypes")
  Collection createCollection(int repeatCount);

  @SuppressWarnings("rawtypes")
  Map createEmptyMap();

  InstanceType<?> getNestedGenericType1();
  InstanceType<?> getNestedGenericType2();

  Call<T, T> findSuitableConstructorWithLeastParameters();

  boolean isEnum();

  T[] getEnumConstants();

  boolean isInterface();

  boolean isAbstract();

  List<Call<T, Object>> getAllSetters();

  List<InstanceField<T>> getAllPublicSettableFieldsOf(T instance);

  TypeToken<?> resolveActualTypeOf(Field field);

  TypeToken<?> resolveActualTypeOf(Method method);

  <TOther> boolean isSameAsThatOf(TOther injectedValue);

  boolean isCompatibleWith(Class<?> clazz);

  boolean isCompatibleWithAnyOf(Class<?>... clazzes);

  boolean isFromPackage(String packageName);

}