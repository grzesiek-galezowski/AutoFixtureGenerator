package autofixture.publicinterface.generators.implementationdetails;

import autofixture.publicinterface.FixtureContract;
import com.google.common.base.Optional;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InterfaceHandler implements InvocationHandler {

  private final FixtureContract fixture;
  private final MethodsInvocationResultCache methodsInvocationResultCache = new MethodsInvocationResultCache();

  public InterfaceHandler(FixtureContract fixture) {
    this.fixture = fixture;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {

    if (isHashCodeMethod(method)) {
      return System.identityHashCode(proxy);
    }

    if (isEqualsMethod(method, arguments)) {
      return proxy == arguments[0];
    }

    if (wasCalledAtLeastOnceOn(proxy, method)) {
      return methodsInvocationResultCache.getResultFor(proxy, method);
    }

    return generateFreshValueFor(proxy, method);
  }

  private boolean isHashCodeMethod(Method method) {
    return "hashCode".equals(method.getName());
  }

  private boolean wasCalledAtLeastOnceOn(Object proxy, Method method) {
    return methodsInvocationResultCache.containAResultFor(proxy, method);
  }

  private boolean isEqualsMethod(Method mtd, Object[] arguments) {
    return "equals".equals(mtd.getName()) && arguments.length == 1;
  }

  private Object generateFreshValueFor(Object proxy, Method method) {
    Optional<Object> freshReturnValue = createReturnValue(fixture, method);
    if (freshReturnValue.isPresent()) {
      methodsInvocationResultCache.setFor(proxy, method, freshReturnValue.get());
    }
    return freshReturnValue.orNull();
  }

  private Optional<Object> createReturnValue(final FixtureContract fixture, Method mtd) {
    Invokable<?, ?> method = Invokable.from(mtd);
    TypeToken<?> returnType = method.getReturnType();

    if (returnType.getRawType() != void.class) {
      return Optional.of(fixture.create(method.getReturnType()));
    } else {
      return Optional.absent();
    }
  }

}
