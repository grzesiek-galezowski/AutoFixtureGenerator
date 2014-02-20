package jfixture.specification;

import org.hamcrest.*;

public class HasLengthMatcher<T> extends TypeSafeMatcher<T[]> {

	private int expectedCount;

	public HasLengthMatcher(int expectedLength) {
		this.expectedCount = expectedLength;
 
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("a collection with count of ").appendValue(expectedCount);
	}

	@Override
	protected boolean matchesSafely(T[] array0) {
		return array0.length == expectedCount;
	}



}
