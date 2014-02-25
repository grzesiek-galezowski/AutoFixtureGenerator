package jfixture.specification.acceptance;

import java.util.ArrayList;

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
		ArrayList<Eon> generatedEons = new ArrayList<>();
		
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		
		assertArrayEquals(new Eon[] { Eon.HADEAN, Eon.ARCHAEAN, Eon.PROTEROZOIC, Eon.PHANEROZOIC, Eon.HADEAN }, 
				generatedEons.toArray(new Eon[] {}));
	}
}
