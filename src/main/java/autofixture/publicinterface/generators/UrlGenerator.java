package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlGenerator implements InstanceGenerator {

	@Override
	public <T> boolean appliesTo(InstanceType<T> instanceType) {
		return instanceType.isRawTypeAssignableFrom(URL.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
		try {
			return (T) new URL("https://github.com/grzesiek-galezowski/jfixture/" + fixture.create(int.class));
		} catch (MalformedURLException e) {
			throw new ObjectCreationException(instanceType, e);
		}
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
	}

}
