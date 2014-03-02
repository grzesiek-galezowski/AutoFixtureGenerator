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
		String result = "";
		for(int i = 0 ; i < length/2 ; ++i) {
			result += alphaCharGenerator.next(fixture);
			result += digitCharGenerator.next(fixture);
		}
		
		if(result.length() < length) {
			result += alphaCharGenerator.next(fixture);
		}
		
		return result;
	}

}
