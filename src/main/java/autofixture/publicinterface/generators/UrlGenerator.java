package autofixture.publicinterface.generators;

import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import autofixture.interfaces.FixtureContract;
import autofixture.exceptions.ObjectCreationException;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(URL.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    try {
      return (T) new URL("https://github.com/grzesiek-galezowski/jfixture/" + fixture.create(int.class));
    } catch (final MalformedURLException e) {
      throw new ObjectCreationException(instanceType, e);
    }
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
