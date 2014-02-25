package jfixture.publicinterface.generators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;
import jfixture.publicinterface.ObjectCreationException;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return true;
	}

	@Override
	public <T> T next(InstanceType<T> type, FixtureContract fixture) {
		Invokable<T, T> currentConstructor = type.findPublicConstructorWithLeastArguments();
		ArrayList<Object> arguments = prepareArgumentsOf(currentConstructor, fixture);
		return createInstanceOf(type, currentConstructor, arguments);
	}

	@SuppressWarnings("unchecked")
	private <T> T createInstanceOf(InstanceType<T> type,
			Invokable<?, ?> currentConstructor, ArrayList<Object> arguments) {
		try {
			T instance = (T) currentConstructor.invoke(null, arguments.toArray());
			return instance;
		} catch (InvocationTargetException | IllegalAccessException e) {
			throw new ObjectCreationException(type, e);
		}
	}

	private <T> ArrayList<Object> prepareArgumentsOf(
			Invokable<T, T> currentConstructor, FixtureContract fixture) {
		ArrayList<Object> arguments = new ArrayList<Object>();
		
		for(Parameter parameter : currentConstructor.getParameters()) {
			  AddInstanceOf(parameter, arguments, fixture);
		}
		
		return arguments;
	}

	private void AddInstanceOf(Parameter parameter,
			ArrayList<Object> arguments, FixtureContract fixture) {
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


}
