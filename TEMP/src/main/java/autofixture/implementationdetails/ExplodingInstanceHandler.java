package autofixture.implementationdetails;

import autofixture.publicinterface.BoomException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ExplodingInstanceHandler implements InvocationHandler {

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args)
      throws RuntimeException {
    throw new BoomException(
        "Everything, like, exploooded, man... you shouldn't touch an exploding instance of " + proxy.getClass());
  }

}
