package jfixture.publicinterface.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.ObjectCreationException;

import com.google.common.base.Optional;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerator implements ComplexObjectGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return true;
	}

	@Override
	public <T> T next(TypeToken<T> type, Fixture fixture)
			throws InstantiationException, IllegalAccessException {

		Invokable<T, T> currentConstructor = findPublicConstructorWithLeastArguments(type);
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

	private <T> ArrayList<Object> prepareArgumentsOf(
			Invokable<T, T> currentConstructor, Fixture fixture) {
		ArrayList<Object> arguments = new ArrayList<Object>();
		
		for(Parameter parameter : currentConstructor.getParameters()) {
			  AddInstanceOf(parameter, arguments, fixture);
		}
		
		return arguments;
	}

	private void AddInstanceOf(Parameter parameter,
			ArrayList<Object> arguments, Fixture fixture) {
		if(IsParameterized(parameter)) {
			  arguments.add(fixture.create(RealTypeOf(parameter)));
		  } else {
			  arguments.add(fixture.create(parameter.getType()));
		  }
	}

	private TypeToken<?> RealTypeOf(Parameter parameter) {
		return TypeToken.of(parameter.getType().getType());
	}

	private boolean IsParameterized(Parameter parameter) {
		return parameter.getType().getType() instanceof ParameterizedType;
	}

	private <T> Invokable<T, T> findPublicConstructorWithLeastArguments(
			TypeToken<T> typeToken) {
		Constructor<?>[] constructors = typeToken.getRawType().getConstructors();
		
		int currentArgumentCount = 10000;
		Optional<Invokable<T, T>> currentConstructor = Optional.absent(); 
		
		for(Constructor<?> constructor : constructors) {
			Invokable<T, T> invokable = typeToken.constructor(constructor);
			int invokableParametersCount = invokable.getParameters().size();
			if(invokable.isPublic() && invokableParametersCount < currentArgumentCount) {
				currentArgumentCount = invokableParametersCount;
				currentConstructor = Optional.of(invokable);
			}
		}

		if(!currentConstructor.isPresent()) {
			throw new ObjectCreationException(typeToken, "Could not find any public constructor");
		}
		return currentConstructor.get();
	}

}
