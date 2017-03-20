package autofixture.publicinterface.generators.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;

public class IdentifierStringGenerator implements
    InlineInstanceGenerator<String> {

  private final InlineInstanceGenerator<Character> alphaCharGenerator;
  private final InlineInstanceGenerator<Character> digitCharGenerator;
  private final int length;

  public IdentifierStringGenerator(
      final InlineInstanceGenerator<Character> alphaCharGenerator,
      final InlineInstanceGenerator<Character> digitCharGenerator, final int length) {
    this.alphaCharGenerator = alphaCharGenerator;
    this.digitCharGenerator = digitCharGenerator;
    this.length = length;
  }

  @Override
  public String next(final FixtureContract fixture) {
    final StringBuilder result = new StringBuilder();
    for (int i = 0; i < length / 2; ++i) {
      result.append(alphaCharGenerator.next(fixture));
      result.append(digitCharGenerator.next(fixture));
    }

    if (result.length() < length) {
      result.append(alphaCharGenerator.next(fixture));
    }

    return result.toString();
  }

}
