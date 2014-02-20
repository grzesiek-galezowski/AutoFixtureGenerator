package jfixture.specification;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import jfixture.publicinterface.Fixture;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PrimitiveTypesGenerationSpecification {
	Fixture fixture = new Fixture();
	
	//TODO generate instances of classes and generic methods
	
	@Theory
	public void ShouldGenerateDifferentValuesEachTime(Class<?> clazz) {
		Object value1 = fixture.create(clazz);
		Object value2 = fixture.create(clazz);
		 
		assertThat(value1, not(is(value2)));
	}

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
	
	
}
