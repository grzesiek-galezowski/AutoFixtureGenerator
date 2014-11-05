package autofixture.publicinterface;

import autofixture.publicinterface.generators.inline.*;
import com.google.common.reflect.TypeToken;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class Generate {
	private static final String AllLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private static final String AllDigits = "1029384756";
	private static final Fixture fixture = new Fixture();
	private static final InlineInstanceGenerator<Integer> portNumberGenerator = new PortNumberGenerator();
	private static final InlineInstanceGenerator<Character> alphaCharGenerator = new CharacterGenerator(
			AllLetters);
	private static final InlineInstanceGenerator<Character> digitCharGenerator = new CharacterGenerator(
			AllDigits);
	
	public static <T> T any(TypeToken<T> instanceType) {
		return fixture.create(instanceType);
	}

    public static <T> T any(Class<T> clazz) {
		return fixture.create(clazz);
	}

	@SafeVarargs
	public static <T> T anyOtherThan(T... omittedValues) {
		return fixture.createWith(new OtherThanGenerator<>(omittedValues));
	}
	
	public static String anyString() {
		return fixture.create(String.class);
	}

	public static String anyStringOfLength(int charactersCount) {
		return fixture.createWith(new StringOfLengthGenerator(charactersCount));
	}

	public static String anyStringNotContaining(String... excludedSubstrings) {
		return fixture.createWith(new StringNotContainingSubstringsGenerator(
				excludedSubstrings));
	}

	public static String anyStringContaining(String str) {
		return fixture.createWith(new StringContainingSubstringGenerator(str));
	}

	public static Character anyAlphaChar() {
		return fixture.createWith(alphaCharGenerator);
	}

	public static Character anyDigitChar() {
		return fixture.createWith(digitCharGenerator);
	}
	
	public static String anyAlphaString() {
		return fixture.createWith(new AlphaStringGenerator(alphaCharGenerator,
				anyString().length()));
	}

	public static String anyAlphaString(int length) {
		return fixture.createWith(new AlphaStringGenerator(alphaCharGenerator,
				length));
	}

	public static String anyIdentifier() {
		return fixture.createWith(new IdentifierStringGenerator(
				alphaCharGenerator, digitCharGenerator, anyString().length()));
	}
	
	public static String anyLegalXmlTagName() {
		return anyIdentifier();
	}
	
	public static Integer anyInteger() {
		return fixture.create(int.class);
	}

	public static Short anyShort() {
		return fixture.create(short.class);
	}

	public static Double anyDouble() {
		return fixture.create(double.class);
	}

	public static Float anyFloat() {
		return fixture.create(float.class);
	}

	public static Long anyLong() {
		return fixture.create(long.class);
	}

	public static Long anyLongOtherThan(long other) {
		return anyOtherThan(other);
	}

	public static String anyStringOtherThan(String other) {
		return anyOtherThan(other);
	}

	public static Integer anyIntegerOtherThan(int other) {
		return anyOtherThan(other);
	}

	public static Short anyShort(short other) {
		return anyOtherThan(other);
	}

	public static Double anyDouble(double other) {
		return anyOtherThan(other);
	}

	public static Float anyFloat(float other) {
		return anyOtherThan(other);
	}

	public static <T> T anyOf(Class<T> enumClass) {
		return fixture.create(enumClass);
	}

	public static Date anyDate() {
		return fixture.create(Date.class);
	}

	public static <T> T anyExploding(Class<T> clazz) {
		return anyExploding(new InstanceOf<T>());
	}

	public static <T> T anyExploding(TypeToken<T> instance) {
		return fixture.createWith(new ExplodingInstanceGenerator<>(instance));
	}

	public static Exception anyException() {
		return fixture.create(Exception.class);
	}

	public static Error anyError() {
		return fixture.create(Error.class);
	}

	public static Boolean anyBoolen() {
		return fixture.create(Boolean.class);
	}

	public static Object anyObject() {
		return fixture.create(Object.class);
	}

	public static URI anyUri() {
		return fixture.create(URI.class);
	}

	public static URL anyUrl() {
		return fixture.create(URL.class);
	}

	public static int anyPort() {
		return fixture.createWith(portNumberGenerator);
	}

	public static InetAddress anyIp() {
		return fixture.create(InetAddress.class);
	}

	public static <T> T[] anyArrayOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<T[]>());
	}

	public static <T> List<T> anyListOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<List<T>>());
	}
	
	public static <T> Iterable<T> anyIterableOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<Iterable<T>>());
	}

	public static <T> Collection<T> anyCollectionOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<Collection<T>>());
	}
	
	public static <T> Set<T> anySetOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<Set<T>>());
	}

	public static <T> Queue<T> anyQueueOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<Queue<T>>());
	}
	
	public static <T> Deque<T> anyDequeOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<Deque<T>>());
	}

	public static <T> SortedSet<T> anySortedSetOf(Class<T> clazz) {
		return fixture.create(new InstanceOf<SortedSet<T>>());
	}
	
	public static <T, V> SortedMap<T, V> anySortedMapOf(Class<T> key, Class<V> value) {
		return fixture.create(new InstanceOf<SortedMap<T, V>>());
	}

	public static <T, V> Map<T, V> anyMapOf(Class<T> key, Class<V> value) {
		return fixture.create(new InstanceOf<Map<T, V>>());
	}
	
	/*

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



    public static T[] ArrayWithout<T>(params T[] excluded)
    {
      return EnumerableWithout(excluded).ToArray();
    }
*/

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
	 */
	

}

