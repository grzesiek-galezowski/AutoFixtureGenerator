package jfixture.specification.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.generators.InterfaceImplementationGenerator;
import jfixture.specification.acceptance.testfixtures.NonGenericInterface;

import org.junit.Test;

public class InterfaceImplementationGenerationSpecification {
	Fixture fixture = new Fixture();
	
	@Test
	public void shouldBeAbleToGenerateInstanceOfSimpleInterfaceWithStableReturnValues() {
		fixture.register(new InterfaceImplementationGenerator());
		
		NonGenericInterface implementation = fixture.create(NonGenericInterface.class);
		NonGenericInterface implementation2 = fixture.create(NonGenericInterface.class);
		
		implementation.doSomething();
		assertThat(implementation.getSomething(), is(not(0)));
		assertThat(implementation.getSomething(), is(implementation.getSomething()));
		assertThat(implementation2.getSomething(), is(not(0)));
		assertThat(implementation2.getSomething(), is(implementation2.getSomething()));
		assertThat(implementation.getSomething(), is(not(implementation2.getSomething())));
		
		
	}
	

}


//TODO non generic ones
//TODO generic ones


