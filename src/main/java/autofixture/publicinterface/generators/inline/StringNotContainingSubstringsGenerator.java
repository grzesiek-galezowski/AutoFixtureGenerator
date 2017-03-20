package autofixture.publicinterface.generators.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;

public class StringNotContainingSubstringsGenerator implements
    InlineInstanceGenerator<String> {

  private final String[] excludedSubstrings;

  public StringNotContainingSubstringsGenerator(final String[] excludedSubstrings) {
    this.excludedSubstrings = excludedSubstrings.clone();
  }

  private static boolean thereAreAnyOccurencesOf(
      final String[] excludedSubstrings,
      final String result) {
    for (final String str : excludedSubstrings) {
      if (result.contains(str)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String next(final FixtureContract fixture) {
    String result;
    do {
      result = fixture.create(String.class);
    } while (thereAreAnyOccurencesOf(excludedSubstrings, result));
    return result;
  }

}
