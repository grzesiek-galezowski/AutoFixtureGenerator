package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class ErrorGenerator implements InstanceGenerator {

	@Override
	public <T> boolean appliesTo(InstanceType<T> instanceType) {
		return instanceType.isRawTypeAssignableFrom(Error.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
		return (T) new Error(fixture.create(String.class));
	}

}
