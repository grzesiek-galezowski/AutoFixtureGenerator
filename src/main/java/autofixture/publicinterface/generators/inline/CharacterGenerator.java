package autofixture.publicinterface.generators.inline;

import autofixture.implementationdetails.CircularList;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;

public class CharacterGenerator implements
    InlineInstanceGenerator<Character> {
  private final CircularList<Character> letters;

  public CharacterGenerator(final String possibleCharacters) {
    this.letters = CircularList
        .fromCharactersIn(possibleCharacters);
  }

  @Override
  public Character next(final FixtureContract fixture) {
    return letters.next();
  }

}
