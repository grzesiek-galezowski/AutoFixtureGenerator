package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class ByteAndCharGenerator implements InstanceGenerator {

	public Byte startingByte = 1;

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.getWrapper() == Byte.class
				|| typeToken.getWrapper() == Character.class;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T)(startingByte++);
	}
	@Override
	public void setOmittingAutoProperties(boolean isOn) {
	}

}
