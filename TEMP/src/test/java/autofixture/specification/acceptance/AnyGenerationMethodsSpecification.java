package autofixture.specification.acceptance;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.BoomException;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.GenericInterface;
import autofixture.specification.acceptance.testfixtures.GenericObject;
import autofixture.specification.acceptance.testfixtures.GenericObject2;
import autofixture.specification.acceptance.testfixtures.NonGenericInterface;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.DayOfWeek;
import java.util.*;

import static autofixture.publicinterface.Generate.*;
import static autofixture.publicinterface.InlineGenerators.*;
import static autofixture.specification.acceptance.TypeHelpers.*;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class AnyGenerationMethodsSpecification {

  @Parameterized.Parameters
  public static List<Object[]> data() {
    return Arrays.asList(new Object[5][0]);
  }

  public AnyGenerationMethodsSpecification() {
  }


  @Test
  public void shouldGenerateEachTimeDifferentString() {
    String str1 = anyString();
    String str2 = anyString();

    assertThat(str1, is(not(str2)));
  }

  @Test
  public void shouldGenerateEachTimeDifferentAlphaString() {
    String str1 = anyAlphaString();
    String str2 = anyAlphaString();

    assertThat(str1, is(not(str2)));

    String str3 = any(alphaString());
    String str4 = any(alphaString());

    assertThat(str3, is(not(str4)));

    String str5 = Any.alphaString();
    String str6 = Any.alphaString();

    assertThat(str5, is(not(str6)));

  }

  @Test
  public void shouldGenerateStringNotContainingGivenSubstring() {
    String str = anyStringNotContaining("1", "2");

    assertThat(str, not(containsString("1")));
    assertThat(str, not(containsString("2")));

    String str2 = any(stringNotContaining("1", "2"));

    assertThat(str2, not(containsString("1")));
    assertThat(str2, not(containsString("2")));

    String str3 = Any.stringNotContaining("1", "2");

    assertThat(str3, not(containsString("1")));
    assertThat(str3, not(containsString("2")));
  }

  @Test
  public void shouldGenerateStringOfGivenLength() {

    int size = 40;
    AssertTwoStringsWithExpectedSizeAreDifferent(
        anyStringOfLength(size),
        anyStringOfLength(size),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        any(stringOfLength(size)),
        any(stringOfLength(size)),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        Any.anonymous(stringOfLength(size)),
        Any.anonymous(stringOfLength(size)),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        Any.stringOfLength(size),
        Any.stringOfLength(size),
        size);

  }

  @Test
  public void shouldGenerateAlphaStringOfGivenLength() {

    int size = 40;
    AssertTwoStringsWithExpectedSizeAreDifferent(
        anyAlphaString(size),
        anyAlphaString(size),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        any(alphaString(size)),
        any(alphaString(size)),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        Any.anonymous(alphaString(size)),
        Any.anonymous(alphaString(size)),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        Any.alphaString(size),
        Any.alphaString(size),
        size);
  }

  private void AssertTwoStringsWithExpectedSizeAreDifferent(String str, String str2, int expectedSize) {
    assertThat(str.length(), equalTo(expectedSize));
    assertThat(str2.length(), equalTo(expectedSize));
    assertThat(str, is(not(str2)));
  }

  @Test
  public void shouldGenerateLongsOtherThanSpecified() {
    assertThat(anyLongOtherThan(56,55), is(not(equalTo(56))));
    assertThat(any(longValue(), otherThan(56l,55l)), is(not(equalTo(56))));
    assertThat(Any.anonymous(longValue(), otherThan(56l,55l)), is(not(equalTo(56))));
    assertThat(Any.longOtherThan(56l,55l), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateIntegersOtherThanSpecified() {
    assertThat(anyIntegerOtherThan(56), is(not(equalTo(56))));
    assertThat(any(intValue(), otherThan(56)), is(not(equalTo(56))));
    assertThat(Any.anonymous(intValue(), otherThan(56)), is(not(equalTo(56))));
    assertThat(Any.intOtherThan(56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateDoublesOtherThanSpecified() {
    assertThat(anyDoubleOtherThan(56), is(not(equalTo(56))));
    assertThat(any(doubleValue(), otherThan(56d)), is(not(equalTo(56))));
    assertThat(Any.anonymous(doubleValue(), otherThan(56d)), is(not(equalTo(56))));
    assertThat(Any.doubleOtherThan(56d), is(not(equalTo(56))));
  }

  @Test
  public void shouldCorrectlyGenerateConstrainedArrays() {
    Integer[] integers = Any.arrayOf(Integer.class, otherThan(123));

    GenericObject<Integer>[] objects = Any.arrayOf(
        new InstanceOf<GenericObject<Integer>>() {
        }, otherThan(new GenericObject<Integer>(123)));

    assertThat(integers, is(not(nullValue())));
    assertThat(objects, is(not(nullValue())));
  }

  @Test
  public void shouldGenerateFloatsOtherThanSpecified() {
    assertThat(anyFloatOtherThan(56), is(not(equalTo(56))));
    assertThat(any(floatValue(), otherThan(56f)), is(not(equalTo(56))));
    assertThat(Any.anonymous(floatValue(), otherThan(56f)), is(not(equalTo(56))));
    assertThat(Any.floatOtherThan(56f), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateStringsOtherThanSpecified() {
    assertThat(anyStringOtherThan("56"), is(not(equalTo("56"))));
    assertThat(any(string(), otherThan("56")), is(not(equalTo("56"))));
    assertThat(Any.anonymous(string(), otherThan("56")), is(not(equalTo("56"))));
    assertThat(Any.stringOtherThan("56"), is(not(equalTo("56"))));
  }

  @Test
  public void shouldGenerateShortsOtherThanSpecified() {
    assertThat(anyShortOtherThan((short)56), is(not(equalTo(56))));
    assertThat(any(shortValue(), otherThan((short) 56)), is(not(equalTo(56))));
    assertThat(Any.anonymous(shortValue(), otherThan((short) 56)), is(not(equalTo(56))));
    assertThat(Any.shortOtherThan((short) 56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateDistinctEnumValues() {
    DayOfWeek dow1 = anyOf(DayOfWeek.class);
    DayOfWeek dow2 = anyOf(DayOfWeek.class);

    assertThat(dow1, is(not(nullValue())));
    assertThat(dow2, is(not(nullValue())));
    assertThat(dow1, is(not(equalTo(dow2))));
  }

  @Test
  public void shouldGenerateDistinctDates() {
    Date date1 = anyDate();
    Date date2 = anyDate();

    assertThat(date1, is(not(nullValue())));
    assertThat(date2, is(not(nullValue())));
    assertThat(date1, is(not(equalTo(date2))));

    Date date3 = Any.date();
    Date date4 = Any.date();

    assertThat(date3, is(not(nullValue())));
    assertThat(date4, is(not(nullValue())));
    assertThat(date3, is(not(equalTo(date4))));

  }

  @Test
  public void shouldGenerateStringContainingGivenSubstring() {
    String str1 = anyStringContaining("1");
    assertThat(str1, containsString("1"));

    String str2 = any(stringContaining("1"));
    assertThat(str2, containsString("1"));

    String str3 = Any.stringContaining("1");
    assertThat(str3, containsString("1"));
  }

  @Test
  public void shouldGenerateEachTimeDifferentInstance() {
    GenericObject<Integer> o1 = any(new InstanceOf<GenericObject<Integer>>() {
    });
    GenericObject<Integer> o2 = any(new InstanceOf<GenericObject<Integer>>() {
    });
    assertThat(o1, is(not(o2)));
  }

  @Test
  public void shouldGenerateItemsOtherThanGivenInstances() {

    Integer notA5 = any(new InstanceOf<Integer>() {
    }, otherThan(5, 10, 15));

    assertThat(notA5, is(not(equalTo(5))));
    assertThat(notA5, is(not(equalTo(10))));
    assertThat(notA5, is(not(equalTo(15))));
  }

  @Test
  public void shouldGenerateItemsOtherThanGivenValues() {

    Integer notA5 = any(intValue(), otherThan(5, 10, 15));

    assertThat(notA5, is(not(equalTo(5))));
    assertThat(notA5, is(not(equalTo(10))));
    assertThat(notA5, is(not(equalTo(15))));
  }


  @Test
  public void shouldGenerateArraysExcludingGivenInstances() {
    List<Integer> list = Arrays.asList(manyAsArrayOf(new InstanceOf<Integer>() {
    }, without(3, 5, 6, 7)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateArraysExcludingGivenValues() {
    List<Integer> list = Arrays.asList(manyAsArrayOf(intValues(), without(3, 5, 6, 7)));

    assertThat(list.size(), is(3));
    assertThat(list, allOf(
            not(hasItem(3)),
            not(hasItem(5)),
            not(hasItem(6)),
            not(hasItem(7))
    ));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateIterablesUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(intValues()));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateIterablesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(new InstanceOf<Integer>() {
    }));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(new InstanceOf<Integer>() {
    }));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(intValues()));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsWithoutSpecifiedValuesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(
      new InstanceOf<Integer>() {
      }, otherThan(1, 2, 3)));

    assertThat(list, hasSize(3));
    assertThat(list, allOf(
            not(hasItem(nullValue())),
            not(hasItem(1)),
            not(hasItem(2)),
            not(hasItem(3))
    ));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsWithoutSpecifiedValuesUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(intValues(), without(3, 5, 6, 7)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateIterablesWithoutSpecifiedValues() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(intValues(), without(1, 2, 3)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(1)));
    assertThat(list, not(hasItem(2)));
    assertThat(list, not(hasItem(3)));
    assertContainsOnlyIntegers(list);
  }


  @Test
  public void shouldGenerateListsUsingClassSignature() {
    List<Integer> list = manyAsListOf(intValues());

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsUsingInstanceSignature() {
    List<Integer> list = manyAsListOf(new InstanceOf<Integer>() {
    });

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsWithoutSpecifiedItemsUsingInstanceSignature() {
    List<Integer> list = manyAsListOf(new InstanceOf<Integer>() {
    }, without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsWithoutSpecifiedItemsUsingClassSignature() {
    List<Integer> list = manyAsListOf(intValues(), without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }



  @Test
  public void shouldGenerateArraysUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsArrayOf(intValues()));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateArraysUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsArrayOf(new InstanceOf<Integer>() {
    }));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateSetsUsingClassSignature() {
    Set<Integer> list = manyAsSetOf(intValues());

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateSetsWithoutSpecifiedItemsUsingClassSignature() {
    Set<Integer> list = manyAsSetOf(intValues(), without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateQueuesUsingClassSignature() {
    Queue<Integer> queue = manyAsQueueOf(intValues());

    assertThat(queue.size(), is(3));
    assertThat(queue, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(queue);
  }

  @Test
  public void shouldGenerateQueuesWithoutSpecifiedItemsUsingClassSignature() {
    Queue<Integer> list = manyAsQueueOf(intValues(), without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateDequesUsingClassSignature() {
    Queue<Integer> collection = manyAsDequeOf(intValues());

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateDequesWithoutSpecifiedItemsUsingClassSignature() {
    Deque<Integer> list = manyAsDequeOf(intValues(), without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }


  @Test
  public void shouldGenerateSortedSetsUsingClassSignature() {
    SortedSet<Integer> collection = manyAsSortedSetOf(intValues());

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateSortedSetsWithoutSpecifiedItemsUsingClassSignature() {
    SortedSet<Integer> list = manyAsSortedSetOf(intValues(), without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }


  @Test
  public void shouldGenerateSortedMapsUsingClassSignature() {
    SortedMap<String, Integer> collection = manyAsSortedMapBetween(string(), intValue());

    assertThat(collection.size(), is(3));
    assertThat(collection.keySet(), not(hasItem(nullValue())));
    assertThat(collection.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection.values());
    assertContainsOnlyStrings(collection.keySet());
  }

  @Test
  public void shouldGenerateMapsUsingClassSignature() {
    Map<String, Integer> collection = manyAsMapBetween(string(), intValue());

    assertThat(collection.size(), is(3));
    assertThat(collection.keySet(), not(hasItem(nullValue())));
    assertThat(collection.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection.values());
    assertContainsOnlyStrings(collection.keySet());
  }

  @Test
  public void shouldGenerateAnIntegerInstanceFromProvided() {
    int[] possibleValues = new int[] {1,3,4,6,7,8};
    int chosen1 = anyFrom(1,3,4,6,7,8);
    int chosen2 = anyFrom(1,3,4,6,7,8);
    int chosen3 = anyFrom(1,3,4,6,7,8);
    int chosen4 = anyFrom(1,3,4,6,7,8);
    int chosen5 = anyFrom(1,3,4,6,7,8);
    int chosen6 = anyFrom(1,3,4,6,7,8);

    assertThat(Arrays.asList(chosen1, chosen2, chosen3, chosen4, chosen5, chosen6), hasItem(1));
    assertThat(Arrays.asList(chosen1, chosen2, chosen3, chosen4, chosen5, chosen6), hasItem(3));
    assertThat(Arrays.asList(chosen1, chosen2, chosen3, chosen4, chosen5, chosen6), hasItem(4));
    assertThat(Arrays.asList(chosen1, chosen2, chosen3, chosen4, chosen5, chosen6), hasItem(6));
    assertThat(Arrays.asList(chosen1, chosen2, chosen3, chosen4, chosen5, chosen6), hasItem(7));
    assertThat(Arrays.asList(chosen1, chosen2, chosen3, chosen4, chosen5, chosen6), hasItem(8));
  }

  @Test
  public void shouldSupportNestedGenericImplementations() {
    //GIVEN
    GenericObject<GenericObject<Integer>> o = any(new InstanceOf<GenericObject<GenericObject<Integer>>>() {});

    //THEN
    assertThat(o.getInstance(), instanceOf(GenericObject.class));
    assertThat(o.getInstance().getInstance(), instanceOf(intValue()));
  }

  @Test
  public void shouldSupportNestedGenericInterfaceImplementations() {
    //GIVEN
    GenericInterface<GenericObject2<Integer>> o =
            any(new InstanceOf<GenericInterface<GenericObject2<Integer>>>() {});

    //THEN
    assertGenericObject2GeneratedCorrectly(o);
  }

  @Test
  public void shouldSupportNestedGenericInterfaceImplementationsUsingAnyClass() {
    //GIVEN
    GenericInterface<GenericObject2<Integer>> o =
            Any.anonymous(new InstanceOf<GenericInterface<GenericObject2<Integer>>>() {});

    //THEN
    assertGenericObject2GeneratedCorrectly(o);
  }


  private void assertGenericObject2GeneratedCorrectly(GenericInterface<GenericObject2<Integer>> o) {
    assertThat(o.getInstance(), instanceOf(GenericObject2.class));
    assertThat(o.getInstance().field, instanceOf(intValue()));
  }

  private void assertContainsOnlyStrings(Collection<String> collection) {
    assertThat(collection.toArray()[0], instanceOf(string()));
    assertThat(collection.toArray()[1], instanceOf(string()));
    assertThat(collection.toArray()[2], instanceOf(string()));
  }

  private void assertContainsOnlyIntegers(Collection<Integer> collection) {
    assertThat(collection.toArray()[0], instanceOf(intValue()));
    assertThat(collection.toArray()[1], instanceOf(intValue()));
    assertThat(collection.toArray()[2], instanceOf(intValue()));
  }



  

/*
  //TODO variations
  public static <T> Set<T> manyAsSetOf(Class<T> clazz) {
    return FIXTURE.create(new InstanceOf<Set<T>>());
  }

  //TODO variations
  public static <T> Queue<T> manyAsQueueOf(Class<T> clazz) {
    return FIXTURE.create(new InstanceOf<Queue<T>>());
  }

  //TODO variations
  public static <T> Deque<T> manyAsDequeOf(Class<T> clazz) {
    return FIXTURE.create(new InstanceOf<Deque<T>>());
  }

  //TODO variations
  public static <T> SortedSet<T> sortedSetOf(Class<T> clazz) {
    return FIXTURE.create(new InstanceOf<SortedSet<T>>());
  }

  //TODO variations
  public static <T, V> SortedMap<T, V> manyAsSortedMapOf(Class<T> key, Class<V> value) {
    return FIXTURE.create(new InstanceOf<SortedMap<T, V>>());
  }

  //TODO variations
  public static <T, V> Map<T, V> manyAsMapOf(Class<T> key, Class<V> value) {

   */

  @Test
  public void shouldGenerateNumbersUsingOnePartMethods() {

    AssertNumbersAreGeneratedCorretly(
            anyInteger(),
            anyDouble(),
            anyFloat(),
            anyChar(),
            anyAlphaChar(),
            anyDigitChar(),
            anyLong(),
            anyShort());
  }

  @Test
  public void shouldGenerateNumbersUsingCompositionalApi() {

    AssertNumbersAreGeneratedCorretly(
            any(intValue()),
            any(doubleValue()),
            any(floatValue()),
            any(charValue()),
            any(alphaChar()),
            any(digitChar()),
            any(longValue()),
            any(shortValue()));

    AssertNumbersAreGeneratedCorretly(
            Any.anonymous(intValue()),
            Any.anonymous(doubleValue()),
            Any.anonymous(floatValue()),
            Any.anonymous(charValue()),
            Any.anonymous(alphaChar()),
            Any.anonymous(digitChar()),
            Any.anonymous(longValue()),
            Any.anonymous(shortValue()));

  }

  @Test
  public void shouldGenerateNumbersFromAnyClass() {

    AssertNumbersAreGeneratedCorretly(
            Any.intValue(),
            Any.doubleValue(),
            Any.floatValue(),
            Any.charValue(),
            Any.alphaChar(),
            Any.digitChar(),
            Any.longValue(),
            Any.shortValue());
  }


  private void AssertNumbersAreGeneratedCorretly(Integer anInt, Double aDouble, Float aFloat, Character aChar, Character alphaChar, Character aDigitChar, Long aLong, Short aShort) {
    assertThat(anInt, is(not(nullValue())));
    assertThat(aDouble, is(not(nullValue())));
    assertThat(aFloat, is(not(nullValue())));
    assertThat(aChar, is(not(nullValue())));
    assertThat(alphaChar, is(not(nullValue())));
    assertThat(aDigitChar, is(not(nullValue())));
    assertThat(aLong, is(not(nullValue())));
    assertThat(aShort, is(not(nullValue())));

    assertThat(anInt, is(instanceOf(intValue())));
    assertThat(aDouble, is(instanceOf(doubleValue())));
    assertThat(aFloat, is(instanceOf(floatValue())));
    assertThat(aChar, is(instanceOf(charValue())));
    assertThat(alphaChar, is(instanceOf(charValue())));
    assertThat(aDigitChar, is(instanceOf(charValue())));
    assertThat(aLong, is(instanceOf(longValue())));
    assertThat(aShort, is(instanceOf(shortValue())));
  }

  @Test
  public void shouldGenerateExplodingInstanceOfInterfacesUsingClassSignature() {
    NonGenericInterface instance = anyExploding(NonGenericInterface.class);

    assertThrows(BoomException.class, instance::doSomething);
    assertThrows(BoomException.class, instance::getSomething);

    NonGenericInterface instance2 = Any.exploding(NonGenericInterface.class);

    assertThrows(BoomException.class, instance2::doSomething);
    assertThrows(BoomException.class, instance2::getSomething);

  }

  @Test
  public void shouldGenerateExplodingInstanceOfInterfacesUsingInstanceSignature() {
    GenericInterface instance = anyExploding(new InstanceOf<GenericInterface>() {});
    assertThrows(BoomException.class, instance::getInstance);

    GenericInterface instance2 = Any.exploding(new InstanceOf<GenericInterface>() {});
    assertThrows(BoomException.class, instance2::getInstance);
  }

  @Test
  public void shouldCreateDifferentPortEachTime() {
    int port1 = anyPort();
    int port2 = anyPort();

    assertThat(port1, is(not(equalTo(port2))));
    assertThat(port1, is(lessThan(65535)));
    assertThat(port2, is(lessThan(65535)));

    int port3 = any(portNumber());
    int port4 = any(portNumber());

    assertThat(port3, is(not(equalTo(port4))));
    assertThat(port3, is(lessThan(65535)));
    assertThat(port4, is(lessThan(65535)));

    int port5 = Any.port();
    int port6 = Any.port();

    assertThat(port5, is(not(equalTo(port6))));
    assertThat(port5, is(lessThan(65535)));
    assertThat(port6, is(lessThan(65535)));
  }

  @Test
  public void lol() {
    createListOf(intValues());
  }

  private <T> List<T> createListOf(Class<T> clazz) {
    return null;
  }

  public void assertThrows(Class exceptionClass, Runnable func) {
    try {
      func.run();
      fail("Expected " + exceptionClass + " being thrown, but got nothing");
    } catch (Exception e) {
      if(e.getClass() != exceptionClass) {
        fail("Expected " + exceptionClass + " being thrown, but got " + e.getClass());
      }
    }
  }

}