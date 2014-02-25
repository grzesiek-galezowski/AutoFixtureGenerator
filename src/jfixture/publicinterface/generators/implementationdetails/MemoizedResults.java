package jfixture.publicinterface.generators.implementationdetails;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MemoizedResults {

	HashMap<Object, HashMap<Method, Object>> data = new HashMap<>();
	
	public boolean containAResultFor(Object proxy, Method mtd) {
		return data.containsKey(proxy) && data.get(proxy).containsKey(mtd);
	}

	public Object getResultFor(Object proxy, Method mtd) {
		return data.get(proxy).get(mtd);
	}

	public void set(Object proxy, Method mtd, Object freshReturnValue) {
		if(!data.containsKey(proxy)) {
			data.put(proxy, new HashMap<Method, Object>());
		}
		
		data.get(proxy).put(mtd, freshReturnValue);
	}

}
