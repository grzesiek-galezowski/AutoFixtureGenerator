package jfixture.publicinterface.generators.inline;

import java.util.Arrays;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;
import jfixture.publicinterface.InstanceOf;

public class OtherThanGenerator<T> implements InlineInstanceGenerator<T> {

	private final T[] omittedValues;

	public OtherThanGenerator(T[] omittedValues) {
		this.omittedValues = omittedValues;
	}

	@Override
	public T next(FixtureContract fixture) {
		T currentValue;
		do {
			currentValue = fixture.create(new InstanceOf<T>());
		} while (Arrays.asList(omittedValues).contains(currentValue));
		return currentValue;
	}

}
