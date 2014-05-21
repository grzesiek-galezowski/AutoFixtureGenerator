package autofixture.specification.acceptance;

import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.GenericObject;
import org.junit.Test;

import static autofixture.publicinterface.Generate.any;
import static autofixture.publicinterface.Generate.anyString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

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
