package autofixture.publicinterface.inline.strings;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;
import autofixture.interfaces.InlineGeneratorsFactory;

/**
 * Created by grzes on 26.11.2016.
 */
public class LowercaseStringGenerator implements InlineInstanceGenerator<String> {
  private final int length;
  private InlineGeneratorsFactory inlineGeneratorsFactory;

  public LowercaseStringGenerator(int length, InlineGeneratorsFactory inlineGeneratorsFactory) {
    this.length = length;
    this.inlineGeneratorsFactory = inlineGeneratorsFactory;
  }

  @Override
  public String next(FixtureContract fixture) {
    return fixture.create(
            inlineGeneratorsFactory.stringOfLength(length)).toLowerCase();
  }

}
