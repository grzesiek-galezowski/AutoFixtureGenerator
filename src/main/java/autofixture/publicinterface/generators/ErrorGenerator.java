package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

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

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
		// TODO Auto-generated method stub
		
	}

}
