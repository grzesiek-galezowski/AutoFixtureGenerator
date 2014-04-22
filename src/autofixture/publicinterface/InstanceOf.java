package autofixture.publicinterface;

import com.google.common.reflect.TypeToken;

public class InstanceOf<T> extends TypeToken<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isInterface() {
		return this.getRawType().isInterface();
	}

}
