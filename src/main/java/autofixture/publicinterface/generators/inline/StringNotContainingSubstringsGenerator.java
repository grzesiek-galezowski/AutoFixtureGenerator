package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

public class StringNotContainingSubstringsGenerator implements
		InlineInstanceGenerator<String> {

	private String[] excludedSubstrings;

	public StringNotContainingSubstringsGenerator(String[] excludedSubstrings) {
		this.excludedSubstrings = excludedSubstrings;
	}

	@Override
	public String next(FixtureContract fixture) {
		String result;
		do {
			result = fixture.create(String.class);
		} while (thereAreAnyOccurencesOf(excludedSubstrings, result));
		return result;
	}
	
	private static boolean thereAreAnyOccurencesOf(
			String[] excludedSubstrings,
			String result) {
		for (String str : excludedSubstrings) {
			if (result.contains(str)) {
				return true;
			}
		}
		return false;
	}

}
