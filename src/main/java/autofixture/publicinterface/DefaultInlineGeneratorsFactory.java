package autofixture.publicinterface;

import autofixture.implementationdetails.InstanceCursor;
import autofixture.interfaces.InlineConstrainedGenerator;
import autofixture.interfaces.InlineGeneratorsFactory;
import autofixture.interfaces.InlineInstanceGenerator;
import autofixture.publicinterface.constraints.OtherThanConstraint;
import autofixture.publicinterface.inline.AbsolutePathGenerator;
import autofixture.publicinterface.inline.CharacterGenerator;
import autofixture.publicinterface.inline.ExplodingInstanceGenerator;
import autofixture.publicinterface.inline.FromGenerator;
import autofixture.publicinterface.inline.GenerationConstrainedByValueRejection;
import autofixture.publicinterface.inline.PortNumberGenerator;
import autofixture.publicinterface.inline.strings.AlphaStringGenerator;
import autofixture.publicinterface.inline.strings.IdentifierStringGenerator;
import autofixture.publicinterface.inline.strings.LowercaseStringGenerator;
import autofixture.publicinterface.inline.strings.StringContainingSubstringGenerator;
import autofixture.publicinterface.inline.strings.StringNotContainingSubstringsGenerator;
import autofixture.publicinterface.inline.strings.StringOfLengthGenerator;
import autofixture.publicinterface.inline.strings.UppercaseStringGenerator;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by astral on 28.03.15.
 */
public class DefaultInlineGeneratorsFactory implements InlineGeneratorsFactory {
  private static final int MANY = 3;
  private static final String ALL_LETTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
  private static final InlineInstanceGenerator<Character> ALPHA_CHAR_GENERATOR = new CharacterGenerator(
      ALL_LETTERS);
  private static final String ALL_DIGITS = "1029384756";
  private static final InlineInstanceGenerator<Character> DIGIT_CHAR_GENERATOR = new CharacterGenerator(
      ALL_DIGITS);
  private final AlphaStringGenerator alphaStringGenerator = new AlphaStringGenerator(ALPHA_CHAR_GENERATOR,
      Any.string().length());
  private final IdentifierStringGenerator identifierStringGenerator = new IdentifierStringGenerator(
      ALPHA_CHAR_GENERATOR, DIGIT_CHAR_GENERATOR, Any.string().length());
  private static final InlineInstanceGenerator<Integer> PORT_NUMBER_GENERATOR = new PortNumberGenerator();
  private final LowercaseStringGenerator lowercaseStringGenerator = new LowercaseStringGenerator(MANY, this);
  private final UppercaseStringGenerator uppercaseStringGenerator = new UppercaseStringGenerator(MANY, this);
  private final AbsolutePathGenerator absolutePathGenerator = new AbsolutePathGenerator(this);
  private static final HashMap<Class, InstanceCursor> INDICES_BY_CLASS = new HashMap<>();

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
    return uppercaseStringGenerator;
  }

  @Override
  public InlineInstanceGenerator<String> lowercaseString() {
    return lowercaseStringGenerator;
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
    return identifierStringGenerator;
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
    public InlineInstanceGenerator<String> seededString(String seed) {
        return fixture -> seed + fixture.create(String.class);
    }

    @Override
  public AlphaStringGenerator alphaString() {
    return alphaStringGenerator;
  }

  @Override
  public <T> InlineInstanceGenerator<T> from(T[] possibleValues) {
    final InstanceCursor cursor = InstanceCursor.from(
        INDICES_BY_CLASS,
        possibleValues[0].getClass(),
        possibleValues.length);
    return new FromGenerator<>(cursor, possibleValues);
  }

  @Override
  public InlineInstanceGenerator<Path> absolutePath() {
    return absolutePathGenerator;
  }

  @Override
  public InlineInstanceGenerator<Path> rootPath() {
    InlineInstanceGenerator<Path> pathInlineInstanceGenerator = f -> Lists.newArrayList(FileSystems.getDefault().getRootDirectories())
        .stream()
        .findFirst().orElse(Paths.get("C:\\"));
    return pathInlineInstanceGenerator;
  }

  @Override
  public InlineInstanceGenerator<Path> relativePath() {
    return fixture -> Paths.get(
        fixture.create(InlineGenerators.identifierString()),
        fixture.create(InlineGenerators.identifierString()),
        fixture.create(InlineGenerators.identifierString()));
  }
}
