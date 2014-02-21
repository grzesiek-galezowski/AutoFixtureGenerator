package jfixture.specification;

import jfixture.publicinterface.Fixture;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.google.common.reflect.TypeToken;

public class EnumTypesGenerationSpecification {
	private Fixture fixture = new Fixture();
	public enum Eon { HADEAN, ARCHAEAN, PROTEROZOIC, PHANEROZOIC }
	
	@Test
	public void ShouldGenerateDistinctEnums() {
		Eon eon1 = fixture.create(TypeToken.of(Eon.class));
		Eon eon2 = fixture.create(TypeToken.of(Eon.class));
		
		assertThat(eon1,not(is(eon2)));
		//TODO this does not work quite well - fix it with circular lists!
	}
}
