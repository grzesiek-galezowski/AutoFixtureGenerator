package autofixture.publicinterface.inline.strings;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;

public class AlphaStringGenerator implements InlineInstanceGenerator<String> {

  private final InlineInstanceGenerator<Character> alphaCharGenerator;
  private final int length;

  public AlphaStringGenerator(
      final InlineInstanceGenerator<Character> alphaCharGenerator, final int length) {
    this.alphaCharGenerator = alphaCharGenerator;
    this.length = length;
  }

  @Override
  public String next(final FixtureContract fixture) {
    final StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; ++i) {
      result.append(alphaCharGenerator.next(fixture));
    }

    return result.toString();
  }

}
