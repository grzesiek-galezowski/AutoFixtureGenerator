package autofixture.publicinterface.generators.implementationdetails;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import autofixture.publicinterface.FixtureContract;

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
		
		if(isHashCodeMethod(mtd)) {
			return System.identityHashCode(proxy);
		} 
		
		if(isEqualsMethod(mtd, arguments)) {
			return proxy == arguments[0];			
		}
		
		if(wasCalledAtLeastOnceOn(proxy, mtd)) {
			return memoizedResults.getResultFor(proxy, mtd);
		}

		return generateFreshValueFor(proxy, mtd);
	}

	private boolean isHashCodeMethod(Method mtd) {
		return "hashCode".equals(mtd.getName());
	}

	private boolean wasCalledAtLeastOnceOn(Object proxy, Method mtd) {
		return memoizedResults.containAResultFor(proxy, mtd);
	}

	private boolean isEqualsMethod(Method mtd, Object[] arguments) {
		return "equals".equals(mtd.getName()) && arguments.length == 1;
	}

	private Object generateFreshValueFor(Object proxy, Method mtd) {
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
