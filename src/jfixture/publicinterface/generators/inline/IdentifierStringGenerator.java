package jfixture.publicinterface.generators.inline;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;

public class IdentifierStringGenerator implements
		InlineInstanceGenerator<String> {

	private final InlineInstanceGenerator<Character> alphaCharGenerator;
	private final InlineInstanceGenerator<Character> digitCharGenerator;
	private final int length;

	public IdentifierStringGenerator(
			InlineInstanceGenerator<Character> alphaCharGenerator,
			InlineInstanceGenerator<Character> digitCharGenerator, int length) {
				this.alphaCharGenerator = alphaCharGenerator;
				this.digitCharGenerator = digitCharGenerator;
				this.length = length;
	}

	@Override
	public String next(FixtureContract fixture) {
		StringBuilder result = new StringBuilder();
		for(int i = 0 ; i < length/2 ; ++i) {
			result.append(alphaCharGenerator.next(fixture));
			result.append(digitCharGenerator.next(fixture));
		}
		
		if(result.length() < length) {
			result.append(alphaCharGenerator.next(fixture));
		}
		
		return result.toString();
	}

}
