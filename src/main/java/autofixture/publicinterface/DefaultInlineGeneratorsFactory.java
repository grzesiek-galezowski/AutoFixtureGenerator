package autofixture.publicinterface;

import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.interfaces.InlineGeneratorsFactory;
import autofixture.interfaces.InlineInstanceGenerator;
import autofixture.publicinterface.constraints.OtherThanConstraint;
import autofixture.publicinterface.generators.inline.*;
import com.google.common.reflect.TypeToken;

/**
 * Created by astral on 28.03.15.
 */
public class DefaultInlineGeneratorsFactory implements InlineGeneratorsFactory {
  private static final String ALL_LETTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
  private static final InlineInstanceGenerator<Character> ALPHA_CHAR_GENERATOR = new CharacterGenerator(
      ALL_LETTERS);
  private static final String ALL_DIGITS = "1029384756";
  private static final InlineInstanceGenerator<Character> DIGIT_CHAR_GENERATOR = new CharacterGenerator(
      ALL_DIGITS);
  private static final InlineInstanceGenerator<Integer> PORT_NUMBER_GENERATOR = new PortNumberGenerator();
  private static final int MANY = 3;

  @Override
  public StringContainingSubstringGenerator stringContaining(final String str) {
    return new StringContainingSubstringGenerator(str);
  }

  @Override
  public AlphaStringGenerator alphaString(final int length) {
    return new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
        length);
  }

  @Override
  public InlineInstanceGenerator<String> uppercaseString() {
    return new UppercaseStringGenerator(MANY, this);
  }

  @Override
  public InlineInstanceGenerator<String> lowercaseString() {
    return new LowercaseStringGenerator(MANY, this);
  }

  @Override
  public InlineInstanceGenerator<String> uppercaseString(final int length) {
    return new UppercaseStringGenerator(length, this);
  }

  @Override
  public InlineInstanceGenerator<String> lowercaseString(final int length) {
    return new LowercaseStringGenerator(length, this);
  }


  @Override
  public IdentifierStringGenerator identifierString() {
    return new IdentifierStringGenerator(
        ALPHA_CHAR_GENERATOR, DIGIT_CHAR_GENERATOR, Generate.anyString().length());
  }

  @Override
  public InlineInstanceGenerator<Character> digitChar() {
    return DIGIT_CHAR_GENERATOR;
  }

  @Override
  public StringNotContainingSubstringsGenerator stringNotContaining(final String... excludedSubstrings) {
    return new StringNotContainingSubstringsGenerator(
        excludedSubstrings);
  }

  @Override
  public InlineInstanceGenerator<Character> alphaChar() {
    return ALPHA_CHAR_GENERATOR;
  }

  @Override
  public InlineInstanceGenerator<String> stringOfLength(final int charactersCount) {
    return new StringOfLengthGenerator(charactersCount);
  }

  @Override
  public <T> ExplodingInstanceGenerator<T> exploding(final TypeToken<T> instance) {
    return new ExplodingInstanceGenerator<>(instance);
  }

  @Override
  public <T> InlineConstrainedGenerator<T> otherThan(final T... omittedValues) {
    return new GenerationConstrainedByValueRejection<>(new OtherThanConstraint<>(omittedValues));
  }

  @Override
  public <T> InlineConstrainedGenerator<T> without(final T... omittedValues) {
    return new GenerationConstrainedByValueRejection<>(new OtherThanConstraint<>(omittedValues));
  }

  @Override
  public InlineInstanceGenerator<Integer> portNumber() {

    return PORT_NUMBER_GENERATOR;
  }

  @Override
  public AlphaStringGenerator alphaString() {
    return new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
        Generate.anyString().length());
  }
}
