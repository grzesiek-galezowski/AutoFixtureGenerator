package jfixture.publicinterface.generators.inline;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;

public class StringOfLengthGenerator implements InlineInstanceGenerator<String> {

	private int charactersCount;

	public StringOfLengthGenerator(int charactersCount) {
		this.charactersCount = charactersCount;
	}

	@Override
	public String next(FixtureContract fixture) {
		StringBuilder result = new StringBuilder();
		while (result.length() < charactersCount) {
			result.append(fixture.create(String.class));
		}
		return result.substring(0, charactersCount);
	}

}
