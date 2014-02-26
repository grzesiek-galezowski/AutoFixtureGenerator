package jfixture.publicinterface.generators;

import java.util.ArrayList;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceType;

public class ConcreteObjectGenerator implements InstanceGenerator {

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return true;
	}

	@Override
	public <T> T next(InstanceType<T> type, FixtureContract fixture) {
		Call<T, T> currentConstructor = type.findPublicConstructorWithLeastParameters();
		T instance = currentConstructor.invokeWithArgumentsCreatedUsing(fixture, null);
		ArrayList<Call<T, Object>> setters = type.getAllSetters();
		for(Call<T, Object> setter : setters) {
			makeBestEffortAttemptToInvoke(setter, instance, fixture);
		}
		return instance;
	}

	private <T> void makeBestEffortAttemptToInvoke(Call<T, Object> setter,
			T instance, FixtureContract fixture) {
		try {
			setter.invokeWithArgumentsCreatedUsing(fixture, instance);
		} catch(Throwable t) {
			//silently invoke any failed attempt
		}
	}
	
}
