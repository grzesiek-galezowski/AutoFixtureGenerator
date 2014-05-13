package autofixture.publicinterface.generators;

import java.math.BigInteger;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class BigIntGenerator implements InstanceGenerator {
	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isAssignableFrom(BigInteger.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T) new BigInteger(fixture.create(Integer.class).toString());
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
		// TODO Auto-generated method stub
		
	}
}
