package autofixture.publicinterface.inline;

import autofixture.publicinterface.DefaultInlineGeneratorsFactory;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AbsolutePathGenerator implements InlineInstanceGenerator<Path> {

  private DefaultInlineGeneratorsFactory defaultInlineGeneratorsFactory;
  private Supplier<Optional<Path>> rootPath = Suppliers.memoize(this::getRoot);

  public AbsolutePathGenerator(DefaultInlineGeneratorsFactory defaultInlineGeneratorsFactory) {
    this.defaultInlineGeneratorsFactory = defaultInlineGeneratorsFactory;
  }

  public Optional<Path> getRoot() {
    return Lists.newArrayList(FileSystems.getDefault().getRootDirectories())
        .stream()
        .findFirst();
  }

  @Override
  public Path next(FixtureContract fixture) {
    return Paths.get(
        this.rootPath.get().orElse(Paths.get("C:\\")).toString(),
        fixture.create(defaultInlineGeneratorsFactory.alphaString()),
        fixture.create(defaultInlineGeneratorsFactory.alphaString()),
        fixture.create(defaultInlineGeneratorsFactory.alphaString()));
  }
}
