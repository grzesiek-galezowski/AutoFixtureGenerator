package jfixture.specification;

import jfixture.publicinterface.Fixture;
import jfixture.specification.testfixtures.ObjectWithPrimitiveConstructorParameters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerationSpecification {

	Fixture fixture = new Fixture();
	
	@Test
	public void ShouldCreateFilledObjectOfTypeXXXX() {
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
	
}
