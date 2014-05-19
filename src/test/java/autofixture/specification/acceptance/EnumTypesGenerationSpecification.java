package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class EnumTypesGenerationSpecification {
	private Fixture fixture = new Fixture();
	public enum Eon { HADEAN, ARCHAEAN, PROTEROZOIC, PHANEROZOIC }
	
	@Test
	public void shouldGenerateDistinctEnumsInACyclicManner() {
		ArrayList<Eon> generatedEons = new ArrayList<>();
		
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
		
		assertArrayEquals(new Eon[] { 
				Eon.HADEAN, 
				Eon.ARCHAEAN, 
				Eon.PROTEROZOIC, 
				Eon.PHANEROZOIC, 
				Eon.HADEAN }, 
				generatedEons.toArray(new Eon[] {}));
	}
}
