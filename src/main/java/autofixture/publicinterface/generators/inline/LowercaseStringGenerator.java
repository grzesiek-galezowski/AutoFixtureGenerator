package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

import static autofixture.publicinterface.InlineGenerators.stringOfLength;

/**
 * Created by grzes on 26.11.2016.
 */
public class LowercaseStringGenerator implements InlineInstanceGenerator<String> {
  private final int length;
  public LowercaseStringGenerator(int length) {
    this.length = length;
  }

  @Override
  public String next(FixtureContract fixture) {
    return fixture.create(stringOfLength(length)).toLowerCase();
  }

}
