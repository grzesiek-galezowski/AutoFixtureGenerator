package autofixture.publicinterface;

public interface InlineInstanceGenerator<T> {
	T next(FixtureContract fixture);
}
