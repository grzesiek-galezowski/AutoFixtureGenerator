package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public interface InstanceGenerator {
	<T> boolean AppliesTo(InstanceType<T> instanceType);
	<T> T next(InstanceType<T> instanceType, Fixture fixture);
}
