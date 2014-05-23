package autofixture.publicinterface;

import com.google.common.reflect.TypeToken;

import java.util.Collection;

public interface FixtureContract {
	<T> T create(Class<T> clazz);
	<T> T create(TypeToken<T> typeToken);
	void register(InstanceGenerator instanceGenerator);
	void clearCustomizations();
	<T> T create(InstanceType<T> instanceType);
    <T> Collection<? super T> createMany(InstanceType<T> type);
    <T> Collection<T> createMany(TypeToken<T> type);
    int getRepeatCount();
}
