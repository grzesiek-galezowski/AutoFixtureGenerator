package jfixture.publicinterface.generators;

import java.util.ArrayList;
import java.util.Collection;

import jfixture.publicinterface.FixtureContract;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Parameter;

public interface Call<TOwnerType, TReturnType> {

	ImmutableList<Parameter> getParameters();
	TReturnType invoke(TOwnerType ownerType, ArrayList<Object> arguments);
	TReturnType invokeWithArgumentsCreatedUsing(
			FixtureContract fixture,
			TOwnerType returnType);
  
}
