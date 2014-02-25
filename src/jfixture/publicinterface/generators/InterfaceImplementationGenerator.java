package jfixture.publicinterface.generators;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;
import jfixture.publicinterface.generators.implementationdetails.InterfaceHandler;

import com.google.common.reflect.Reflection;

public class InterfaceImplementationGenerator implements InstanceGenerator{
	
	@Override
	public <T> boolean AppliesTo(InstanceType<T> instanceType) {
		boolean isInterface = instanceType.isInterface();
		return isInterface;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> instanceType, final FixtureContract fixture) {
		Object proxy = Reflection.newProxy(instanceType.getRawType(), new InterfaceHandler(
				fixture));
		return (T) proxy;
	}

}
