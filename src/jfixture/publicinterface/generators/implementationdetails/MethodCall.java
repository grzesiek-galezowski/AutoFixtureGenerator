package jfixture.publicinterface.generators.implementationdetails;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.ObjectCreationException;
import jfixture.publicinterface.generators.Call;

public class MethodCall<TOwnerType, TReturnType> implements Call<TOwnerType, TReturnType> {

	private final Invokable<TOwnerType, TReturnType> invokable;

	public MethodCall(Invokable<TOwnerType, TReturnType> invokable) {
		this.invokable = invokable;
	}

	public static <TElement1, TElement2>   
	Call<TElement1, TElement2> to(Invokable<TElement1, TElement2> invokable) {
			return new MethodCall<TElement1, TElement2>(invokable);
	}

	@Override
	public ImmutableList<Parameter> getParameters() {
		return invokable.getParameters();
	}

	@Override
	public TReturnType invoke(TOwnerType ownerType, ArrayList<Object> arguments) {
		try {
			return invokable.invoke(ownerType, arguments.toArray());
		} catch (InvocationTargetException | IllegalAccessException e) {
			throw new ObjectCreationException(
					new ConcreteInstanceType(invokable.getOwnerType()), e);
		}
	}
	
	public TReturnType invokeWithArgumentsCreatedUsing(
			FixtureContract fixture, 
			TOwnerType returnType) {
		ArrayList<Object> arguments = prepareArgumentsOf(this, fixture);
		TReturnType instance = invoke(returnType, arguments);
		return instance;
	}

	private ArrayList<Object> prepareArgumentsOf(
			Call<TOwnerType, TReturnType> invokable, FixtureContract fixture) {
		ArrayList<Object> arguments = new ArrayList<Object>();
		
		for(Parameter parameter : invokable.getParameters()) {
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

	@Override
	public TReturnType invokeWithArgumentsCreatedUsing(FixtureContract fixture) {
		return invokeWithArgumentsCreatedUsing(fixture, null);
	}

	
}
