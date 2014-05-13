package autofixture.publicinterface.generators;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

public class ConcreteObjectGenerator implements InstanceGenerator {

	private boolean omittingAutoProperties = false;

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return true;
	}

	@Override
	public <T> T next(InstanceType<T> type, FixtureContract fixture) {
		T instance = createInstanceOf(type, fixture);
		try {
			if(!omittingAutoProperties) {
				makeBestEffortAttemptToInvokeAllSettersOn(instance, type, fixture);
				makeBestEffortAttemptToSetAllPublicFields(instance, type, fixture);
			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ObjectCreationException(type, e);
		}
		return instance;
	}

	private <T> void makeBestEffortAttemptToSetAllPublicFields(T instance,
			InstanceType<T> type, FixtureContract fixture) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<Field> publicFields = type.getAllPublicFields();
		for(Field publicField : publicFields) {
			set(publicField, instance, fixture);
		}
		
	}
	
	
	private <T> T createInstanceOf(InstanceType<T> type, FixtureContract fixture) {
		Call<T, T> currentConstructor = type.findPublicConstructorWithLeastParameters();
		T instance = currentConstructor.invokeWithArgumentsCreatedUsing(fixture);
		return instance;
	}

	private <T> void makeBestEffortAttemptToInvokeAllSettersOn(T instance,
			InstanceType<T> type, FixtureContract fixture) {
		ArrayList<Call<T, Object>> setters = type.getAllSetters();
		for(Call<T, Object> setter : setters) {
			makeBestEffortAttemptToInvoke(setter, instance, fixture);
		}
	}

	private <T> void set(Field field, T instance, FixtureContract fixture) throws IllegalArgumentException, IllegalAccessException {
		if(IsParameterized(field)) {
			field.set(instance, fixture.create(realTypeOf(field)));
 		} else {
			field.set(instance, fixture.create(field.getType()));
		}
	}

	private TypeToken<?> realTypeOf(Field field) {
		return TypeToken.of(field.getGenericType());
	}

		
	
	private boolean IsParameterized(Field field) {
		return field.getGenericType() instanceof ParameterizedType;
	}

	private <T> void makeBestEffortAttemptToInvoke(Call<T, Object> setter,
			T instance, FixtureContract fixture) {
		try {
			setter.invokeWithArgumentsCreatedUsing(fixture, instance);
		} catch(Throwable t) {
			//silently invoke any failed attempt
		}
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
		this.omittingAutoProperties = isOn;
		
	}
	
}
