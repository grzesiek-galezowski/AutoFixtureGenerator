package autofixture.publicinterface.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineGeneratorsFactory;
import autofixture.interfaces.InlineInstanceGenerator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativePathGenerator implements InlineInstanceGenerator<Path> {

  private final InlineGeneratorsFactory inlineGeneratorsFactory;

  public RelativePathGenerator(InlineGeneratorsFactory inlineGeneratorsFactory) {
    this.inlineGeneratorsFactory = inlineGeneratorsFactory;
  }

  @Override
  public Path next(FixtureContract fixture) {
    return Paths.get(
        fixture.create(inlineGeneratorsFactory.identifierString()),
        fixture.create(inlineGeneratorsFactory.identifierString()),
        fixture.create(inlineGeneratorsFactory.identifierString()));
  }
}
