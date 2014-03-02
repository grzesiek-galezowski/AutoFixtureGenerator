package jfixture.publicinterface.generators.inline;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;
import jfixture.publicinterface.generators.implementationdetails.CircularList;

public class CharacterGenerator implements
		InlineInstanceGenerator<Character> {
	private final CircularList<Character> letters;

	public CharacterGenerator(String possibleCharacters) {
		this.letters = CircularList
				.fromCharactersIn(possibleCharacters);
	}

	@Override
	public Character next(FixtureContract fixture) {
		return letters.next();
	}

}
