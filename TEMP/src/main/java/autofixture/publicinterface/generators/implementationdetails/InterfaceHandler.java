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

  public InterfaceHandler(final FixtureContract fixture, final InstanceType<T> instanceType) {
    this.fixture = fixture;
    this.instanceType = instanceType;
  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] arguments) throws Exception {

    final TypeToken<?> returnType = instanceType.resolveActualTypeOf(method);

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

  private boolean isHashCodeMethod(final Method method) {
    return "hashCode".equals(method.getName());
  }

  private boolean wasCalledAtLeastOnceOn(final Object proxy, final Method method) {
    return methodsInvocationResultCache.containAResultFor(proxy, method);
  }

  private boolean isEqualsMethod(final Method mtd, final Object[] arguments) {
    return "equals".equals(mtd.getName()) && arguments.length == 1;
  }

  //TODO check case when new X<Integer>().GetValue() and new X<String>().GetValue() afterwards - does this work?
  private Object generateFreshValueFor(final Object proxy, final Method method, final TypeToken<?> returnType) {
    final Optional<Object> freshReturnValue = createReturnValue(fixture, returnType);
    if (freshReturnValue.isPresent()) {
      methodsInvocationResultCache.setFor(proxy, method, freshReturnValue.get());
    }
    return freshReturnValue.orNull();
  }

  private Optional<Object> createReturnValue(final FixtureContract fixture, final TypeToken<?> returnType) {
    if (returnType.getRawType() != void.class) {
      final Object o = fixture.create(returnType);
      return Optional.of(o);
    } else {
      return Optional.absent();
    }
  }

}
