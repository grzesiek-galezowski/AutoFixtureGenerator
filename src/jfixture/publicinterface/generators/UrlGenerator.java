package jfixture.publicinterface.generators;

import java.net.MalformedURLException;
import java.net.URL;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;
import jfixture.publicinterface.ObjectCreationException;

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

}
