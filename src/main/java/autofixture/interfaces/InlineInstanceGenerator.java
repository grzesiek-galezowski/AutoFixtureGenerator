package autofixture.interfaces;

public interface InlineInstanceGenerator<T> {
  T next(FixtureContract fixture);
}
