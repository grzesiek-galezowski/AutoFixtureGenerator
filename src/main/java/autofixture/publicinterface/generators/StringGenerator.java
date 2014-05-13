package autofixture.publicinterface.generators;

import java.util.UUID;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class StringGenerator implements InstanceGenerator {
	
	@SuppressWarnings("unchecked")
	public <T> T next(InstanceType<T> clazz, FixtureContract fixture) {
		return (T)UUID.randomUUID().toString();
	}

	@Override
	public <T> boolean appliesTo(InstanceType<T> clazz) {
		return clazz.isRawTypeAssignableFrom(String.class);
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
		// TODO Auto-generated method stub
		
	}
	
}
