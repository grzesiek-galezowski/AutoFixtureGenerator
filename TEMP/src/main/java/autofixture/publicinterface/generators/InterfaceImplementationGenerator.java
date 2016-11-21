package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.generators.implementationdetails.InterfaceHandler;
import com.google.common.reflect.Reflection;

public class InterfaceImplementationGenerator implements InstanceGenerator {

  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    final boolean isInterface = instanceType.isInterface();
    return isInterface;
  }

  @SuppressWarnings("unchecked")
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    final Object proxy = Reflection.newProxy(instanceType.getRawType(), new InterfaceHandler(
        fixture, instanceType));
    return (T) proxy;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
