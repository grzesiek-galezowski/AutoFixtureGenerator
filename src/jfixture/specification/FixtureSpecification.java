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
public class FixtureSpecification {
	Fixture fixture = new Fixture();
	
	@DataPoint
	public static Class<Integer> intClass = int.class;
	@DataPoint
	public static Class<String> stringClass = String.class;
	@DataPoint
	public static Class<Double> doubleClass = Double.class;
	@DataPoint
	public static Class<Float> floatClass = Float.class;
	@DataPoint
	public static Class<Byte> byteClass = Byte.class;
	@DataPoint
	public static Class<Date> dateClass = Date.class;
	@DataPoint
	public static Class<Calendar> calendarClass = Calendar.class;
	@DataPoint
	public static Class<BigDecimal> bigDecimalClass = BigDecimal.class;
	@DataPoint
	public static Class<BigInteger> bigIntClass = BigInteger.class;
	@DataPoint
	public static Class<Object> objectClass = Object.class;
	
	//TODO generate instances of classes and generic methods
	
	
	
	@Theory
	public void ShouldGenerateDifferentValuesEachTime(Class<?> clazz) {
		Object value1 = fixture.create(clazz);
		Object value2 = fixture.create(clazz);
		
		assertThat(value1, not(is(value2)));
	}
	
	
}
