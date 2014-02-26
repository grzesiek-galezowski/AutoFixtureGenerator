package jfixture.publicinterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import jfixture.publicinterface.generators.Call;

import com.google.common.collect.ImmutableCollection;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

public interface InstanceType<T> {
	InstanceType<?> getArrayElementType();
	<TAssignable> boolean isAssignableFrom(
			Class<TAssignable> clazz);
	Class<? super T> getRawType();
	TypeToken<? super T> getToken();
	Type getType();
	boolean isArray();
	Invokable<T, T> constructor(Constructor<?> constructor);
	Object createArray(Object[] objects);
	boolean IsAssignableTo(Class<?> clazz);
	boolean isRawTypeAssignableFrom(Class<?> clazz);
	@SuppressWarnings("rawtypes")
	Collection createCollection();
	InstanceType<?> getNestedGenericType();
	Call<T, T> findPublicConstructorWithLeastParameters();
	boolean isEnum();
	T[] getEnumConstants();
	boolean isInterface();
	ArrayList<Call<T, Object>> getAllSetters();
}