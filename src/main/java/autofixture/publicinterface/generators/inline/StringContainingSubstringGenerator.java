package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

public class StringContainingSubstringGenerator implements
		InlineInstanceGenerator<String> {

	private final String str;

	public StringContainingSubstringGenerator(String str) {
		this.str = str;
	}

	@Override
	public String next(FixtureContract fixture) {
		return fixture.create(String.class) + str + fixture.create(String.class);
	}

}
