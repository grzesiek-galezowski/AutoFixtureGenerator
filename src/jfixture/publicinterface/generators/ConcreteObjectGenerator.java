package jfixture.publicinterface.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.ObjectCreationException;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerator implements CompoundObjectGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return true;
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture)
			throws InstantiationException, IllegalAccessException {
		Constructor<?>[] constructors = typeToken.getRawType().getConstructors();
		
		int currentArgumentCount = 10000;
		Invokable<?, ?> currentConstructor = null; 
		
		for(Constructor<?> constructor : constructors) {
			Invokable<?, ?> invokable = Invokable.from(constructor);
			int invokableParametersCount = invokable.getParameters().size();
			if(invokable.isPublic() && invokableParametersCount < currentArgumentCount) {
				currentArgumentCount = invokableParametersCount;
				currentConstructor = invokable;
			}
		}
		
		try {
			T instance = (T) currentConstructor.invoke(null, 3, "a", 3.0);
			return instance;
		} catch (InvocationTargetException e) {
			throw new ObjectCreationException(typeToken, e);
		}
	}

}
