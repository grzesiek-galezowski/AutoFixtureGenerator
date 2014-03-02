package jfixture.publicinterface.generators.inline;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;
import jfixture.publicinterface.generators.implementationdetails.CircularList;

public class AlphaCharacterGenerator implements
		InlineInstanceGenerator<Character> {
	private final CircularList<Character> letters = CircularList.fromCharactersIn("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
	
	@Override
	public Character next(FixtureContract fixture) {
		return letters.next();
	}

}
