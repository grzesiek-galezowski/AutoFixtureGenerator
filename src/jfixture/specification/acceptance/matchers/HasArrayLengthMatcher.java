package jfixture.specification.acceptance.matchers;

import org.hamcrest.*;

public class HasArrayLengthMatcher<T> extends TypeSafeMatcher<T[]> {

	private int expectedCount;

	public HasArrayLengthMatcher(int expectedLength) {
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
