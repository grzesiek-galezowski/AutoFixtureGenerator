package autofixture.specification.acceptance;

import autofixture.publicinterface.*;
import com.google.common.reflect.TypeToken;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class PrimitiveTypesGenerationSpecification {
  @DataPoint
  public static Class<Integer> intClass = int.class;
  @DataPoint
  public static Class<Integer> intClass2 = Integer.class;
  @DataPoint
  public static Class<String> stringClass = String.class;
  @DataPoint
  public static Class<Double> doubleClass = double.class;
  @DataPoint
  public static Class<Double> doubleClass2 = Double.class;
  @DataPoint
  public static Class<Float> floatClass = float.class;
  @DataPoint
  public static Class<Float> floatClass2 = Float.class;
  @DataPoint
  public static Class<Byte> byteClass = byte.class;
  @DataPoint
  public static Class<Byte> byteClass2 = Byte.class;
  @DataPoint
  public static Class<Date> dateClass = Date.class;
  @DataPoint
  public static Class<Calendar> calendarClass = Calendar.class;
  @DataPoint
  public static Class<BigDecimal> bigDecimalClass = BigDecimal.class;
  @DataPoint
  public static Class<BigInteger> bigIntClass = BigInteger.class;
  @DataPoint
  public static Class<Short> shortClass = short.class;
  @DataPoint
  public static Class<Short> shortClass2 = Short.class;
  @DataPoint
  public static Class<Long> longClass = long.class;
  @DataPoint
  public static Class<Long> longClass2 = Long.class;
  @DataPoint
  public static Class<Boolean> booleanClass = boolean.class;
  @DataPoint
  public static Class<Boolean> booleanClass1 = Boolean.class;
  @DataPoint
  public static Class<Character> charClass = char.class;
  @DataPoint
  public static Class<Character> charClass1 = Character.class;
  @DataPoint
  public static Class<UUID> uuidClass = UUID.class;
  @DataPoint
  public static Class<Locale> localeClass = Locale.class;
  Fixture fixture = new Fixture();
  Mockery context = new Mockery();

  @Theory
  public void shouldGenerateDifferentValuesEachTime(Class<?> clazz) {
    System.out.println(clazz);
    Object value1 = fixture.create(TypeToken.of(clazz));
    Object value2 = fixture.create(TypeToken.of(clazz));

    assertThat(value1, notNullValue());
    assertThat(value2, notNullValue());
    assertThat(value1, not(is(value2)));
  }

  @Test
  public void shouldGivePrecedenceToRegisteredGenerators() {
    fixture.register(new InstanceGenerator() {

      @SuppressWarnings("unchecked")
      @Override
      public <T> T next(InstanceType<T> type, FixtureContract fixture) {
        return (T) Integer.valueOf(9999);
      }

      @Override
      public <T> boolean appliesTo(InstanceType<T> type) {
        return type.isRawTypeAssignableFrom(int.class);
      }

      @Override
      public void setOmittingAutoProperties(boolean isOn) {
      }
    });

    Integer anInt = fixture.create(TypeToken.of(int.class));

    assertThat(anInt, is(9999));
  }

  @Test
  public void shouldGivePrecedenceToRegisteredGeneratorsWhenCreatingWithInlineGenerator() {
    final InlineInstanceGenerator<Integer> inlineGeneratorStub =
      (InlineInstanceGenerator<Integer>) (context.mock(InlineInstanceGenerator.class));

    context.checking(new Expectations() {{
      allowing(inlineGeneratorStub).next(fixture);
      will(returnValue(9999));
    }});

    Integer anInt = fixture.createWith(inlineGeneratorStub);

    assertThat(anInt, is(9999));
  }

  @Test
  public void shouldAllowGeneratingStringsWithPrefixForBetterIdentificationInErrorMessages() {
    String prefixedString1 = fixture.create("prefix");
    String prefixedString2 = fixture.create("prefix");

    assertTrue(prefixedString1.startsWith("prefix"));
    assertTrue(prefixedString2.startsWith("prefix"));
    assertThat(prefixedString1, is(not(prefixedString2)));
  }

}
