package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;
import autofixture.publicinterface.generators.implementationdetails.CircularList;

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
