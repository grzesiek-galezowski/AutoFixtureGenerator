package autofixture.publicinterface.generators.implementationdetails;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MethodsInvocationResultCache {

	HashMap<Object, HashMap<Method, Object>> data = new HashMap<>();
	
	public boolean containAResultFor(Object proxy, Method mtd) {
		return data.containsKey(proxy) && data.get(proxy).containsKey(mtd);
	}

	public Object getResultFor(Object proxy, Method method) {
		return data.get(proxy).get(method);
	}

	public void setFor(Object proxy, Method method, Object freshReturnValue) {
		if(!data.containsKey(proxy)) {
			data.put(proxy, new HashMap<>());
		}
		
		data.get(proxy).put(method, freshReturnValue);
	}

}
