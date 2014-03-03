package jfixture.implementationdetails;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import jfixture.publicinterface.BoooomException;

public class ExplodingInstanceHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		throw new BoooomException(
				"Everything, like, exploooded, man... you shouldn't touch an exploding instance of " + proxy);
	}

}
