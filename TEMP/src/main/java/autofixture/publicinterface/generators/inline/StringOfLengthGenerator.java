package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

public class StringOfLengthGenerator implements InlineInstanceGenerator<String> {

  private final int charactersCount;

  public StringOfLengthGenerator(final int charactersCount) {
    this.charactersCount = charactersCount;
  }

  @Override
  public String next(final FixtureContract fixture) {
    final StringBuilder result = new StringBuilder();
    while (result.length() < charactersCount) {
      result.append(fixture.create(String.class));
    }
    return result.substring(0, charactersCount);
  }

}
