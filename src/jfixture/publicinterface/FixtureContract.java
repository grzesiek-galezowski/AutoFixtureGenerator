package jfixture.publicinterface;

import jfixture.publicinterface.generators.GeneratorsFactory;
import jfixture.publicinterface.generators.GeneratorsPipeline;
import jfixture.publicinterface.generators.InstanceGenerator;

import com.google.common.reflect.TypeToken;

public interface FixtureContract {
	<T> T create(Class<T> clazz);
	<T> T create(TypeToken<T> typeToken);
	void register(InstanceGenerator instanceGenerator);
	void clearCustomizations();
	<T> T create(InstanceType<T> instanceType);
}
