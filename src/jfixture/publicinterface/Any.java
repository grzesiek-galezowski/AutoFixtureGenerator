package jfixture.publicinterface;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jfixture.publicinterface.generators.implementationdetails.CircularList;
import jfixture.publicinterface.generators.implementationdetails.ConcreteInstanceType;
import jfixture.publicinterface.generators.inline.CharacterGenerator;
import jfixture.publicinterface.generators.inline.AlphaStringGenerator;
import jfixture.publicinterface.generators.inline.ExplodingInstanceGenerator;
import jfixture.publicinterface.generators.inline.IdentifierStringGenerator;
import jfixture.publicinterface.generators.inline.OtherThanGenerator;
import jfixture.publicinterface.generators.inline.PortNumberGenerator;
import jfixture.publicinterface.generators.inline.StringContainingSubstringGenerator;
import jfixture.publicinterface.generators.inline.StringNotContainingSubsctringsGenerator;
import jfixture.publicinterface.generators.inline.StringOfLengthGenerator;

import com.google.common.reflect.Reflection;

public class Any {
	private static final String AllLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private static final String AllDigits = "1029384756";
	private static final Fixture fixture = new Fixture();
	private static final InlineInstanceGenerator<Integer> portNumberGenerator = new PortNumberGenerator();
	private static final InlineInstanceGenerator<Character> alphaCharGenerator = new CharacterGenerator(
			AllLetters);
	private static final InlineInstanceGenerator<Character> digitCharGenerator = new CharacterGenerator(
			AllDigits);
	
	public static String string() {
		return fixture.create(String.class);
	}

	public static String stringOfLength(int charactersCount) {
		return fixture.createWith(new StringOfLengthGenerator(charactersCount));
	}

	public static String stringNotContaining(String... excludedSubstrings) {
		return fixture.createWith(new StringNotContainingSubsctringsGenerator(
				excludedSubstrings));
	}

	public static String stringContaining(String str) {
		return fixture.createWith(new StringContainingSubstringGenerator(str));
	}

	public static Character alphaChar() {
		return fixture.createWith(alphaCharGenerator);
	}

	public static Character digitChar() {
		return fixture.createWith(digitCharGenerator);
	}
	
	public static String alphaString() {
		return fixture.createWith(new AlphaStringGenerator(alphaCharGenerator,
				string().length()));
	}

	public static String alphaString(int length) {
		return fixture.createWith(new AlphaStringGenerator(alphaCharGenerator,
				length));
	}

	public static String identifier() {
		return fixture.createWith(new IdentifierStringGenerator(
				alphaCharGenerator, digitCharGenerator, string().length()));
	}
	
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
		return fixture.createWith(new OtherThanGenerator<T>(omittedValues));
	}

	public static Date date() {
		return fixture.create(Date.class);
	}

	// TODO add exploding for generic types
	public static <T> T exploding(Class<T> clazz) {
		return exploding(new InstanceOf<T>());
	}

	public static <T> T exploding(InstanceOf<T> instance) {
		return fixture.createWith(new ExplodingInstanceGenerator<T>(instance));
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
	
	/*
    public static IEnumerable<T> Enumerable<T>()
    {
      return Enumerable<T>(length: Many);
    }

    public static IEnumerable<T> Enumerable<T>(int length)
    {
      return AddManyTo(new List<T>(), length);
    }

    public static IEnumerable<T> EnumerableWithout<T>(params T[] excluded)
    {
      var result = new List<T>
      {
        OtherThan(excluded), 
        OtherThan(excluded), 
        OtherThan(excluded)
      };
      return result;
    }

    public static T[] Array<T>()
    {
      return Array<T>(Many);
    }

    public static T[] Array<T>(int length)
    {
      return Enumerable<T>(length).ToArray();
    }


    public static T[] ArrayWithout<T>(params T[] excluded)
    {
      return EnumerableWithout(excluded).ToArray();
    }

    public static List<T> List<T>()
    {
      return List<T>(Many);
    }

    public static List<T> List<T>(int length)
    {
      return Enumerable<T>(length).ToList();
    }

    public static SortedList<TKey, TValue> SortedList<TKey, TValue>()
    {
      return SortedList<TKey, TValue>(Many);
    }

    public static SortedList<TKey, TValue> SortedList<TKey, TValue>(int length)
    {
      var list = new SortedList<TKey, TValue>();
      for (int i = 0; i < length; ++i)
      {
        list.Add(Any.Instance<TKey>(), Any.Instance<TValue>());
      }
      return list;
    }


    public static ISet<T> Set<T>(int length)
    {
      return new HashSet<T>(Enumerable<T>(length));
    }

    public static ISet<T> Set<T>()
    {
      return Set<T>(Many);
    }

    public static ISet<T> SortedSet<T>(int length)
    {
      return new SortedSet<T>(Enumerable<T>(length));
    }

    public static ISet<T> SortedSet<T>()
    {
      return SortedSet<T>(Many);
    }

    public static Dictionary<TKey, TValue> Dictionary<TKey, TValue>(int length)
    {
      var dict = new Dictionary<TKey, TValue>();
      for (int i = 0; i < length; ++i)
      {
        dict.Add(Any.Instance<TKey>(), Any.Instance<TValue>());
      }
      return dict;
    }

    public static Dictionary<TKey, TValue> Dictionary<TKey, TValue>()
    {
      return Dictionary<TKey, TValue>(Many);
    }

    public static SortedDictionary<TKey, TValue> SortedDictionary<TKey, TValue>(int length)
    {
      var dict = new SortedDictionary<TKey, TValue>();
      for (int i = 0; i < length; ++i)
      {
        dict.Add(Any.Instance<TKey>(), Any.Instance<TValue>());
      }
      return dict;
    }

    public static SortedDictionary<TKey, TValue> SortedDictionary<TKey, TValue>()
    {
      return SortedDictionary<TKey, TValue>(Many);
    }

    public static IEnumerable<T> EnumerableSortedDescending<T>(int length)
    {
      return SortedSet<T>(length).ToList();
    }

    public static IEnumerable<T> EnumerableSortedDescending<T>()
    {
      return EnumerableSortedDescending<T>(Many);
    }
*/

}
