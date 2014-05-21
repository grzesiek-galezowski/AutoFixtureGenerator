package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class IntGenerator implements InstanceGenerator {

	public Integer startingInteger = 1;

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(int.class)
				|| typeToken.isRawTypeAssignableFrom(Integer.class)
				|| typeToken.isRawTypeAssignableFrom(short.class)
				|| typeToken.isRawTypeAssignableFrom(Short.class)
				|| typeToken.isRawTypeAssignableFrom(long.class)
				|| typeToken.isRawTypeAssignableFrom(Long.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		return (T)(startingInteger++);
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
	}

}
