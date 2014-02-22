package jfixture.publicinterface.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.ObjectCreationException;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerator implements CompoundObjectGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return true;
	}

	@Override
	public <T> T next(TypeToken<T> type, Fixture fixture)
			throws InstantiationException, IllegalAccessException {

		Invokable<?, ?> currentConstructor = findPublicConstructorWithLeastArguments(type);
		ArrayList<Object> arguments = prepareArgumentsOf(currentConstructor, fixture);
		return createInstanceOf(type, currentConstructor, arguments);
		
	}

	private <T> T createInstanceOf(TypeToken<T> typeToken,
			Invokable<?, ?> currentConstructor, ArrayList<Object> arguments)
			throws IllegalAccessException {
		try {
			T instance = (T) currentConstructor.invoke(null, arguments.toArray());
			return instance;
		} catch (InvocationTargetException e) {
			throw new ObjectCreationException(typeToken, e);
		}
	}

	private ArrayList<Object> prepareArgumentsOf(
			Invokable<?, ?> currentConstructor, Fixture fixture) {
		ArrayList<Object> arguments = new ArrayList<Object>();
		
		for(Parameter parameter : currentConstructor.getParameters()) {
			arguments.add(fixture.create(parameter.getType()));
		}
		return arguments;
	}

	private <T> Invokable<?, ?> findPublicConstructorWithLeastArguments(
			TypeToken<T> typeToken) {
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

		if(currentConstructor == null) {
			throw new ObjectCreationException(typeToken, "Could not find any public constructor");
		}
		return currentConstructor;
	}

}
