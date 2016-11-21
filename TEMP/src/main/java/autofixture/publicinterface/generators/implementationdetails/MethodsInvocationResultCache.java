package autofixture.publicinterface.generators.implementationdetails;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodsInvocationResultCache {

  private final Map<Object, Map<Method, Object>> data = new HashMap<>();

  public boolean containAResultFor(final Object proxy, final Method mtd) {
    return data.containsKey(proxy) && data.get(proxy).containsKey(mtd);
  }

  public Object getResultFor(final Object proxy, final Method method) {
    return data.get(proxy).get(method);
  }

  public void setFor(final Object proxy, final Method method, final Object freshReturnValue) {
    if (!data.containsKey(proxy)) {
      data.put(proxy, new HashMap<>());
    }

    data.get(proxy).put(method, freshReturnValue);
  }

}
