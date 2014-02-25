package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;

public interface InstanceGenerator {
	<T> boolean AppliesTo(InstanceType<T> instanceType);
	<T> T next(InstanceType<T> instanceType, FixtureContract fixture);
}
