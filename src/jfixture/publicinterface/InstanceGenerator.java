package jfixture.publicinterface;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;

public interface InstanceGenerator {
	<T> boolean appliesTo(InstanceType<T> instanceType);
	<T> T next(InstanceType<T> instanceType, FixtureContract fixture);
}
