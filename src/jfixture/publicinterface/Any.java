package jfixture.publicinterface;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jfixture.publicinterface.generators.implementationdetails.ConcreteInstanceType;
import jfixture.publicinterface.generators.inline.PortNumberGenerator;

import com.google.common.reflect.Reflection;

public class Any {
	private static final Fixture fixture = new Fixture();
	private static InlineInstanceGenerator<Integer> portNumberGenerator = new PortNumberGenerator();

	public static String string() {
		return fixture.create(String.class);
	}

	public static String stringOfLength(int charactersCount) {
		StringBuilder result = new StringBuilder();
		while (result.length() < charactersCount) {
			result.append(string());
		}
		return result.substring(0, charactersCount);
	}

	public static String stringNotContaining(String... excludedSubstrings) {
		String result;
		do {
			result = string();
		} while (thereAreAnyOccurencesOf(excludedSubstrings, result));
		return result;
	}

/* TODO
    public static string StringContaining(string str)
    {
      return String() + str + String();
    }

    public static string AlphaString()
    {
      return AlphaString(String().Length);
    }

    public static string AlphaString(int maxLength)
    {
      var result = System.String.Empty;
      for (var i = 0; i < maxLength; ++i)
      {
        result += AlphaChar();
      }
      return result;
    }

    public static string Identifier()
    {
      string result = AlphaChar().ToString(CultureInfo.InvariantCulture);
      for (var i = 0; i < 5; ++i)
      {
        result += DigitChar();
        result += AlphaChar();
      }
      return result;
    }

    public static char AlphaChar()
    {
      return Letters.Next();
    }

    public static char DigitChar()
    {
      return Digits.Next();
    }*/
	
	
	public static Integer integer() {
		return fixture.create(int.class);
	}

	public static Short shortInteger() {
		return fixture.create(short.class);
	}

	public static Double doubleNumber() {
		return fixture.create(double.class);
	}

	public static Float floatingPointNumber() {
		return fixture.create(float.class);
	}

	public static Long longInteger() {
		return fixture.create(long.class);
	}

	public static Long longIntegerOtherThan(long other) {
		return otherThan(other);
	}

	public static String stringOtherThan(String other) {
		return otherThan(other);
	}

	public static Integer integerOtherThan(int other) {
		return otherThan(other);
	}

	public static Short shortInteger(short other) {
		return otherThan(other);
	}

	public static Double doubleNumber(double other) {
		return otherThan(other);
	}

	public static Float floatingPointNumber(float other) {
		return otherThan(other);
	}

	public static <T> T[] arrayOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<T[]>());
	}

	public static <T> List<T> listOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<List<T>>());
	}

	public static <T> T of(Class<T> enumClass) {
		return fixture.create(enumClass);
	}

	@SafeVarargs
	public static <T> T otherThan(T... omittedValues) {
		T currentValue;
		do {
			currentValue = fixture.create(new InstanceOf<T>());
		} while (Arrays.asList(omittedValues).contains(currentValue));
		return currentValue;
	}

	public static Date date() {
		return fixture.create(Date.class);
	}

	// TODO add exploding for generic types
	public static <T> T exploding(Class<T> clazz) {
		return exploding(new InstanceOf<T>());
	}

	@SuppressWarnings("unchecked")
	public static <T> T exploding(InstanceOf<T> instance) {
		if (instance.isInterface()) {
			throw new InterfacesNotSupportedException(
					"Exploding instances can be created out of interfaces only!");
		} else {
			return (T) Reflection.newProxy(instance.getRawType(),
					new ExplodingInstanceHandler());
		}
	}

	public static Exception exception() {
		return fixture.create(Exception.class);
	}

	public static Error error() {
		return fixture.create(Error.class);
	}

	public static Boolean bool() {
		return fixture.create(Boolean.class);
	}

	public static Object object() {
		return fixture.create(Object.class);
	}

	public static URI uri() {
		return fixture.create(URI.class);
	}

	public static URL url() {
		return fixture.create(URL.class);
	}

	public static int port() {
		return fixture.createWith(portNumberGenerator);
	}

	public static InetAddress ip() {
		return fixture.create(InetAddress.class);
	}

	/*
	 * TODO
	 * 
	 * public static T From<T>(params T[] possibleValues) { var
	 * latestArraysWithPossibleValues = _arrayElementPicking.For<T>();
	 * 
	 * if (!latestArraysWithPossibleValues.Contain(possibleValues)) {
	 * latestArraysWithPossibleValues.Add(possibleValues); }
	 * 
	 * var result =
	 * latestArraysWithPossibleValues.PickNextElementFrom(possibleValues);
	 * return result; }
	 * 
	 * public static T ValueOf<T>() { //bug: add support for creating generic
	 * structs with interfaces return Generator.Create<T>(); }
	 * 
	 * public static string LegalXmlTagName() { return Identifier(); }
	 * 
	 * public static MethodInfo Method() { return ValueOf<MethodInfo>(); }
	 * 
	 * public static Type Type() { return ValueOf<Type>(); }
	 * 
	 * public static T InstanceOf<T>() { return
	 * FakeChain<T>.NewInstance(CachedGeneration, NestingLimit).Resolve(); }
	 * 
	 * public static T Instance<T>() { return
	 * FakeChain<T>.NewInstance(CachedGeneration, NestingLimit).Resolve(); }
	 */
	
	private static boolean thereAreAnyOccurencesOf(
			String[] excludedSubstrings,
			String result) {
		for (String str : excludedSubstrings) {
			if (result.contains(str)) {
				return true;
			}
		}
		return false;
	}
	
}
