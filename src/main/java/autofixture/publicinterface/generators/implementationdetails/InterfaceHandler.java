package autofixture.publicinterface.generators.implementationdetails;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceType;
import com.google.common.base.Optional;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InterfaceHandler<T> implements InvocationHandler {

  private final FixtureContract fixture;
  private final InstanceType<T> instanceType;
  private final MethodsInvocationResultCache methodsInvocationResultCache = new MethodsInvocationResultCache();

  public InterfaceHandler(FixtureContract fixture, InstanceType<T> instanceType) {
    this.fixture = fixture;
    this.instanceType = instanceType;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] arguments) throws Exception {

    TypeToken<?> returnType = instanceType.resolveActualTypeOf(method);

    if (isHashCodeMethod(method)) {
      return System.identityHashCode(proxy);
    }

    if (isEqualsMethod(method, arguments)) {
      return proxy == arguments[0];
    }

    if (wasCalledAtLeastOnceOn(proxy, method)) {
      return methodsInvocationResultCache.getResultFor(proxy, method);
    }

    return generateFreshValueFor(proxy, method, returnType);
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

  //TODO check case when new X<Integer>().GetValue() and new X<String>().GetValue() afterwards - does this work?
  private Object generateFreshValueFor(Object proxy, Method method, TypeToken<?> returnType) {
    Optional<Object> freshReturnValue = createReturnValue(fixture, returnType);
    if (freshReturnValue.isPresent()) {
      methodsInvocationResultCache.setFor(proxy, method, freshReturnValue.get());
    }
    return freshReturnValue.orNull();
  }

  private Optional<Object> createReturnValue(final FixtureContract fixture, TypeToken<?> returnType) {
    if (returnType.getRawType() != void.class) {
      Object o = fixture.create(returnType);
      return Optional.of(o);
    } else {
      return Optional.absent();
    }
  }

}
