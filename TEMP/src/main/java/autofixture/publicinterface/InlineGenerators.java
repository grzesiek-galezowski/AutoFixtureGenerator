package autofixture.publicinterface;

import autofixture.publicinterface.constraints.OtherThanConstraint;
import autofixture.publicinterface.generators.inline.AlphaStringGenerator;
import autofixture.publicinterface.generators.inline.CharacterGenerator;
import autofixture.publicinterface.generators.inline.ExplodingInstanceGenerator;
import autofixture.publicinterface.generators.inline.GenerationConstrainedByValueRejection;
import autofixture.publicinterface.generators.inline.IdentifierStringGenerator;
import autofixture.publicinterface.generators.inline.PortNumberGenerator;
import autofixture.publicinterface.generators.inline.StringContainingSubstringGenerator;
import autofixture.publicinterface.generators.inline.StringNotContainingSubstringsGenerator;
import autofixture.publicinterface.generators.inline.StringOfLengthGenerator;
import com.google.common.reflect.TypeToken;

/**
 * Created by astral on 28.03.15.
 */
public class InlineGenerators {
  private static final String ALL_LETTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
  private static final InlineInstanceGenerator<Character> ALPHA_CHAR_GENERATOR = new CharacterGenerator(
      ALL_LETTERS);
  private static final String ALL_DIGITS = "1029384756";
  private static final InlineInstanceGenerator<Character> DIGIT_CHAR_GENERATOR = new CharacterGenerator(
      ALL_DIGITS);
  private static final InlineInstanceGenerator<Integer> PORT_NUMBER_GENERATOR = new PortNumberGenerator();

  public static StringContainingSubstringGenerator stringContaining(final String str) {
    return new StringContainingSubstringGenerator(str);
  }

  public static AlphaStringGenerator alphaString() {
    return new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
        Generate.anyString().length());
  }

  public static AlphaStringGenerator alphaString(final int length) {
    return new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
        length);
  }

  public static IdentifierStringGenerator identifierString() {
    return new IdentifierStringGenerator(
        ALPHA_CHAR_GENERATOR, DIGIT_CHAR_GENERATOR, Generate.anyString().length());
  }

  public static InlineInstanceGenerator<Character> digitChar() {
    return DIGIT_CHAR_GENERATOR;
  }

  public static StringNotContainingSubstringsGenerator stringNotContaining(final String... excludedSubstrings) {
    return new StringNotContainingSubstringsGenerator(
        excludedSubstrings);
  }

  public static InlineInstanceGenerator<Character> alphaChar() {
    return ALPHA_CHAR_GENERATOR;
  }

  public static StringOfLengthGenerator stringOfLength(final int charactersCount) {
    return new StringOfLengthGenerator(charactersCount);
  }

  public static <T> ExplodingInstanceGenerator<T> exploding(final TypeToken<T> instance) {
    return new ExplodingInstanceGenerator<>(instance);
  }

  public static <T> InlineConstrainedGenerator<T> otherThan(final T... omittedValues) {
    return new GenerationConstrainedByValueRejection<>(new OtherThanConstraint<>(omittedValues));
  }

  public static <T> InlineConstrainedGenerator<T> without(final T... omittedValues) {
    return new GenerationConstrainedByValueRejection<>(new OtherThanConstraint<>(omittedValues));
  }

  public static InlineInstanceGenerator<Integer> portNumber() {
    return PORT_NUMBER_GENERATOR;
  }
}
