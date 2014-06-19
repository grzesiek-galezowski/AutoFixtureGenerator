package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class IntGenerator implements InstanceGenerator {

	public Integer startingInteger = 1;

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.getWrapper() == Integer.class
                || typeToken.getWrapper() == Short.class
                || typeToken.getWrapper() == Long.class;
	}

    public static <T> boolean instanceType(InstanceType<T> typeToken) {
        return typeToken.getWrapper() == Integer.class
                || typeToken.getWrapper() == Short.class
                || typeToken.getWrapper() == Long.class;
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
