package autofixture.publicinterface;

import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.interfaces.InlineGeneratorsFactory;
import autofixture.interfaces.InlineInstanceGenerator;
import com.google.common.reflect.TypeToken;

import java.nio.file.Path;

/**
 * Created by astral on 28.03.15.
 */
public class InlineGenerators {

  private static final InlineGeneratorsFactory INLINE_GENERATORS_FACTORY = new DefaultInlineGeneratorsFactory();

  public static InlineInstanceGenerator<String> stringContaining(final String str) {
    return INLINE_GENERATORS_FACTORY.stringContaining(str);
  }

  public static InlineInstanceGenerator<String> alphaString() {
    return INLINE_GENERATORS_FACTORY.alphaString();
  }

  public static InlineInstanceGenerator<String> alphaString(final int length) {
    return INLINE_GENERATORS_FACTORY.alphaString(length);
  }

  public static InlineInstanceGenerator<String> uppercaseString() {
    return INLINE_GENERATORS_FACTORY.uppercaseString();
  }

  public static InlineInstanceGenerator<String> lowercaseString() {
    return INLINE_GENERATORS_FACTORY.lowercaseString();
  }

  public static InlineInstanceGenerator<String> uppercaseString(final int length) {
    return INLINE_GENERATORS_FACTORY.uppercaseString(length);
  }

  public static InlineInstanceGenerator<String> lowercaseString(final int length) {
    return INLINE_GENERATORS_FACTORY.lowercaseString(length);
  }


  public static InlineInstanceGenerator<String> identifierString() {
    return INLINE_GENERATORS_FACTORY.identifierString();
  }

  public static InlineInstanceGenerator<Character> digitChar() {
    return INLINE_GENERATORS_FACTORY.digitChar();
  }

  public static InlineInstanceGenerator<String> stringNotContaining(final String... excludedSubstrings) {
    return INLINE_GENERATORS_FACTORY.stringNotContaining(excludedSubstrings);
  }

  public static InlineInstanceGenerator<Character> alphaChar() {
    return INLINE_GENERATORS_FACTORY.alphaChar();
  }

  public static InlineInstanceGenerator<String> stringOfLength(final int charactersCount) {
    return INLINE_GENERATORS_FACTORY.stringOfLength(charactersCount);
  }

  public static <T> InlineInstanceGenerator<T> exploding(final TypeToken<T> instance) {
    return INLINE_GENERATORS_FACTORY.exploding(instance);
  }

  public static <T> InlineConstrainedGenerator<T> otherThan(final T... omittedValues) {
    return INLINE_GENERATORS_FACTORY.otherThan(omittedValues);
  }

  public static <T> InlineConstrainedGenerator<T> without(final T... omittedValues) {
    return INLINE_GENERATORS_FACTORY.without(omittedValues);
  }

  public static InlineInstanceGenerator<Integer> portNumber() {
    return INLINE_GENERATORS_FACTORY.portNumber();
  }

  public static <T> InlineInstanceGenerator<T> from(final T[] possibleValues) {
    return INLINE_GENERATORS_FACTORY.from(possibleValues);
  }

  public static InlineInstanceGenerator<String> seededString(String seed) {
    return INLINE_GENERATORS_FACTORY.seededString(seed);
  }

  public static InlineInstanceGenerator<Path> relativePath() {
    return INLINE_GENERATORS_FACTORY.relativePath();
  }

}
