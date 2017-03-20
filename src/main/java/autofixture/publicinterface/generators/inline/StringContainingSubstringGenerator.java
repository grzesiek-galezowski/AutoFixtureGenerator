package autofixture.publicinterface.generators.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;

public class StringContainingSubstringGenerator implements
    InlineInstanceGenerator<String> {

  private final String str;

  public StringContainingSubstringGenerator(final String str) {
    this.str = str;
  }

  @Override
  public String next(final FixtureContract fixture) {
    return fixture.create(String.class) + str + fixture.create(String.class);
  }

}
