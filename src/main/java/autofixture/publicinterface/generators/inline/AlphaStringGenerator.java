package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

public class AlphaStringGenerator implements InlineInstanceGenerator<String> {

  private final InlineInstanceGenerator<Character> alphaCharGenerator;
  private final int length;

  public AlphaStringGenerator(
    InlineInstanceGenerator<Character> alphaCharGenerator, int length) {
    this.alphaCharGenerator = alphaCharGenerator;
    this.length = length;
  }

  @Override
  public String next(FixtureContract fixture) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; ++i) {
      result.append(alphaCharGenerator.next(fixture));
    }

    return result.toString();
  }

}
