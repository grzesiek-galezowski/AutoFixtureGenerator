package jfixture.specification.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashSet;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.FixtureContract;
import jfixture.specification.acceptance.matchers.HasArrayLengthMatcher;
import jfixture.specification.acceptance.testfixtures.ObjectWithCollectionConstructorParameters;
import jfixture.specification.acceptance.testfixtures.ObjectWithGenericConstructorParameters;
import jfixture.specification.acceptance.testfixtures.ObjectWithPrimitiveConstructorParameters;

import org.junit.Test;

import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerationSpecification {

	Fixture fixture = new Fixture();
	HashSet<Double>[] doubleSets = array(new HashSet<Double>());
	
	@Test
	public void shouldCreateFilledObjectWithPrimitiveValuesInConstructor() {
		ObjectWithPrimitiveConstructorParameters obj1 
			= fixture.create(TypeToken.of(ObjectWithPrimitiveConstructorParameters.class));
		ObjectWithPrimitiveConstructorParameters obj2 
			= fixture.create(TypeToken.of(ObjectWithPrimitiveConstructorParameters.class));
		
		assertThat(obj1.intParameter, is(not(0)));
		assertThat(obj2.intParameter, is(not(0)));
		
		assertThat(obj1.doubleParameter, is(not(0.0)));
		assertThat(obj2.doubleParameter, is(not(0.0)));

		assertThat(obj1.stringParameter, is(notNullValue()));
		assertThat(obj2.stringParameter, is(notNullValue()));

		assertThat(obj1.stringParameter, is(not("")));
		assertThat(obj2.stringParameter, is(not("")));
		
		assertThat(obj1.intParameter, is(not(obj2.intParameter)));
		assertThat(obj1.doubleParameter, is(not(obj2.doubleParameter)));
		assertThat(obj1.stringParameter, is(not(obj2.stringParameter)));
	}
	
	@Test
	public void shouldCreateFilledObjectWithCollectionParametersInConstructor() {
		ObjectWithCollectionConstructorParameters obj1 
			= fixture.create(TypeToken.of(ObjectWithCollectionConstructorParameters.class));
		
		assertThat(obj1.intListParameter.toArray(), hasManyItems());
		
		assertThat(obj1.doubleSetSetParameter.toArray(), hasManyItems());

		Object[] nestedSet1Obj1 = obj1.doubleSetSetParameter.toArray(doubleSets)[0].toArray();
		Object[] nestedSet2Obj1 = obj1.doubleSetSetParameter.toArray(doubleSets)[1].toArray();
		Object[] nestedSet3Obj1 = obj1.doubleSetSetParameter.toArray(doubleSets)[2].toArray();
		
		assertThat(nestedSet1Obj1, hasManyItems());
		assertThat(nestedSet2Obj1, hasManyItems());
		assertThat(nestedSet3Obj1, hasManyItems());

		assertThat(obj1.stringArrayParameter, hasManyItems());
		assertThat(obj1.stringArrayParameter[0], hasManyItems());
		assertThat(obj1.stringArrayParameter[1], hasManyItems());
		assertThat(obj1.stringArrayParameter[2], hasManyItems());
	}

	private HasArrayLengthMatcher<Object> hasManyItems() {
		return new HasArrayLengthMatcher<>(3);
	}
	
	@Test
	public void shouldCreateInstancesOfClassesWithCustomGenericConstructorParameters() {
		ObjectWithGenericConstructorParameters obj 
			= fixture.create(TypeToken.of(ObjectWithGenericConstructorParameters.class));
		
		assertThat(obj.parameter1.instance, is(notNullValue()));
		assertThat(obj.parameter1.instance, is(not("")));
	}
	
	@SafeVarargs
	static <E> E[] array(E... array)
	{
	    return array;
	}
	
}
