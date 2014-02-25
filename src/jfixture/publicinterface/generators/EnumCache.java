package jfixture.publicinterface.generators;

import jfixture.publicinterface.InstanceType;

public interface EnumCache {

	<T> void registerIfNotPresent(InstanceType<T> instanceType);

	<T> T retrieveNextValueOf(InstanceType<T> instanceType);

}
