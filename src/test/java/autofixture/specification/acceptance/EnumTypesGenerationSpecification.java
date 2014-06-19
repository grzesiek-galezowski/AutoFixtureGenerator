package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EnumTypesGenerationSpecification {
	private Fixture fixture = new Fixture();
	public enum Eon { HADEAN, ARCHAEAN, PROTEROZOIC, PHANEROZOIC }
	
	@Test
	public void shouldGenerateDistinctEnumValues() {
		HashSet<Eon> generatedEons = new HashSet<>();

        for(Eon ignored : Eon.values()) {
          generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
        }

        assertThat(generatedEons.size(), equalTo(Eon.values().length));
	}

    @Test
    public void shouldGenerateSameElementAsFirstWhenItRunsOutOfElements() {
        ArrayList<Eon> generatedEons = new ArrayList<>();

        for(Eon eon : Eon.values()) {
            generatedEons.add(fixture.create(TypeToken.of(Eon.class)));
        }

        Eon overflowEon = fixture.create(Eon.class);

        assertThat(overflowEon, equalTo(generatedEons.get(0)));
    }

}
