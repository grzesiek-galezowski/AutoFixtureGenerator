package autofixture.publicinterface.generators.implementationdetails;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.ObjectCreationException;
import autofixture.publicinterface.generators.Call;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class MethodCall<TOwnerType, TReturnType> implements Call<TOwnerType, TReturnType> {

	private final Invokable<TOwnerType, TReturnType> invokable;

	public MethodCall(Invokable<TOwnerType, TReturnType> invokable) {
		this.invokable = invokable;
	}

	public static <TElement1, TElement2>   
	Call<TElement1, TElement2> to(Invokable<TElement1, TElement2> invokable) {
			return new MethodCall<>(invokable);
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
					new ConcreteInstanceType<>(invokable.getOwnerType()), e);
		} catch (IllegalArgumentException e) {
			throw new ObjectCreationException(
					new ConcreteInstanceType<>(invokable.getOwnerType()),
							"Inner classes are not supported for now. \nCaused by " + e.toString());
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
		ArrayList<Object> arguments = new ArrayList<>();
		
		for(Parameter parameter : invokable.getParameters()) {
			  AddInstanceOf(parameter, arguments, fixture);
		}
		
		return arguments;
	}

	private void AddInstanceOf(Parameter parameter,
			ArrayList<Object> arguments, FixtureContract fixture) {
		if(IsParameterized(parameter)) {
			  arguments.add(fixture.create(realTypeOf(parameter)));
		  } else {
			  arguments.add(fixture.create(parameter.getType()));
		  }
	}
	
	private TypeToken<?> realTypeOf(Parameter parameter) {
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
