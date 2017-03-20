package autofixture.publicinterface.generators.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineGeneratorsFactory;
import autofixture.interfaces.InlineInstanceGenerator;

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
