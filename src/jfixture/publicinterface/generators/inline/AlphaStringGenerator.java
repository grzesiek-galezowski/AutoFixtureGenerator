package jfixture.publicinterface.generators.inline;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;

public class AlphaStringGenerator implements InlineInstanceGenerator<String> {

	private final InlineInstanceGenerator<Character> alphaCharGenerator;
	private final int length;

	public AlphaStringGenerator(
			InlineInstanceGenerator<Character> alphaCharGenerator, int length) {
				this.alphaCharGenerator = alphaCharGenerator;
				this.length = length;
	}

	@Override
	public String next(FixtureContract fixture) {
		String result = "";
		for (int i = 0; i < length; ++i) {
			result += alphaCharGenerator.next(fixture);
		}
		return result;
	}

}
