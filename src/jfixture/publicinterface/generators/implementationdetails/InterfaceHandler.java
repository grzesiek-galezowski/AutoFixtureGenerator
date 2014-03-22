package jfixture.publicinterface.generators.implementationdetails;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import jfixture.publicinterface.FixtureContract;

import com.google.common.base.Objects;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

public class InterfaceHandler implements InvocationHandler {

	private final FixtureContract fixture;
	private final MemoizedResults memoizedResults = new MemoizedResults();
	
	public InterfaceHandler(FixtureContract fixture) {
		this.fixture = fixture;
	}

	@Override
	public Object invoke(Object proxy, Method mtd, Object[] arguments)	throws Throwable {
		Object returnValue = null;
		
		if("hashCode".equals(mtd.getName())) {
			returnValue = System.identityHashCode(proxy);
		} else if("equals".equals(mtd.getName()) && arguments.length == 1) {
			returnValue = proxy == arguments[0];			
		} else if(memoizedResults.containAResultFor(proxy, mtd)) {
			returnValue = memoizedResults.getResultFor(proxy, mtd);
		} else {
			returnValue = generateValueFor(proxy, mtd);
		}
		
		return returnValue;
	}

	private Object generateValueFor(Object proxy, Method mtd) {
		Object returnValue;
		Object freshReturnValue = createReturnValue(fixture, mtd);
		if(freshReturnValue != null) {
			memoizedResults.set(proxy, mtd, freshReturnValue);
		}
		returnValue = freshReturnValue;
		return returnValue;
	}

	private Object createReturnValue(final FixtureContract fixture, Method mtd) {
		Invokable<?,?> method = Invokable.from(mtd);
		TypeToken<?> returnType = method.getReturnType();
		
		
		if(returnType.getRawType() != void.class) {
			return fixture.create(method.getReturnType());
		} else {
			return null;
		}
	}

}
