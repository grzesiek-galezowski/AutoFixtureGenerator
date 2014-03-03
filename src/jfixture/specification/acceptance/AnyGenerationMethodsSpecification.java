package jfixture.specification.acceptance;

import static jfixture.publicinterface.AnyGenerationMethods.any;
import static jfixture.publicinterface.AnyGenerationMethods.anyString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import jfixture.publicinterface.InstanceOf;
import jfixture.specification.acceptance.testfixtures.GenericObject;

import org.junit.Test;

public class AnyGenerationMethodsSpecification {
	
	@Test
	public void shouldGenerateEachTimeDifferentString() {
		String str1 = anyString();
		String str2 = anyString();
		
		assertThat(str1, is(not(str2)));
	}
	
	@Test
	public void shouldGenerateEachTimeDifferentInstance() {
		GenericObject<Integer> o1 = any(new InstanceOf<GenericObject<Integer>>() {});
		GenericObject<Integer> o2 = any(new InstanceOf<GenericObject<Integer>>() {});
		
		assertThat(o1, is(not(o2)));
	}

}
