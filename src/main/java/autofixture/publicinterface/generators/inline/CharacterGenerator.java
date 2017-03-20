package autofixture.publicinterface.generators.inline;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;
import autofixture.implementationdetails.CircularList;

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
