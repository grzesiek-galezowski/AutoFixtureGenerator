package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;
import autofixture.publicinterface.generators.StringGenerator;

import static autofixture.publicinterface.InlineGenerators.stringOfLength;

/**
 * Created by grzes on 26.11.2016.
 */
public class UppercaseStringGenerator implements InlineInstanceGenerator<String> {
  private final int length;

  public UppercaseStringGenerator(int length) {

    this.length = length; //bug
  }

  @Override
  public String next(FixtureContract fixture) {
    return fixture.create(stringOfLength(length)).toUpperCase();
  }
}
