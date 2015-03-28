package autofixture.specification.acceptance;

import autofixture.publicinterface.BoomException;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.GenericInterface;
import autofixture.specification.acceptance.testfixtures.GenericObject;
import autofixture.specification.acceptance.testfixtures.GenericObject2;
import autofixture.specification.acceptance.testfixtures.NonGenericInterface;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.*;

import static autofixture.publicinterface.Generate.*;
import static autofixture.publicinterface.InlineGenerators.*;
import static autofixture.specification.acceptance.TypeHelpers.*;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class AnyGenerationMethodsSpecification {

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
  }

  @Test
  public void shouldGenerateStringNotContainingGivenSubstring() {
    String str = anyStringNotContaining("1", "2");

    assertThat(str, not(containsString("1")));
    assertThat(str, not(containsString("2")));

    String str2 = any(stringNotContaining("1", "2"));

    assertThat(str2, not(containsString("1")));
    assertThat(str2, not(containsString("2")));
  }

  @Test
  public void shouldGenerateStringOfGivenLength() {
    String str = anyStringOfLength(40);
    String str2 = anyStringOfLength(40);

    assertThat(str.length(), equalTo(40));
    assertThat(str2.length(), equalTo(40));
    assertThat(str, is(not(str2)));

    String str3 = any(stringOfLength(40));
    String str4 = any(stringOfLength(40));

    assertThat(str3.length(), equalTo(40));
    assertThat(str4.length(), equalTo(40));
    assertThat(str3, is(not(str4)));
  }

  @Test
  public void shouldGenerateAlphaStringOfGivenLength() {
    String str = anyAlphaString(40);
    String str2 = anyAlphaString(40);

    assertThat(str.length(), equalTo(40));
    assertThat(str2.length(), equalTo(40));
    assertThat(str, is(not(str2)));

    String str3 = any(alphaString(40));
    String str4 = any(alphaString(40));

    assertThat(str3.length(), equalTo(40));
    assertThat(str4.length(), equalTo(40));
    assertThat(str3, is(not(str4)));
  }

  @Test
  public void shouldGenerateLongsOtherThanSpecified() {
    assertThat(anyLongOtherThan(56), is(not(equalTo(56))));
    assertThat(any(longValue(), otherThan(56l)), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateIntegersOtherThanSpecified() {
    assertThat(anyIntegerOtherThan(56), is(not(equalTo(56))));
    assertThat(any(intValue(), otherThan(56)), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateDoublesOtherThanSpecified() {
    assertThat(anyDoubleOtherThan(56), is(not(equalTo(56))));
    assertThat(any(doubleValue(), otherThan(56d)), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateFloatsOtherThanSpecified() {
    assertThat(anyFloatOtherThan(56), is(not(equalTo(56))));
    assertThat(any(floatValue(), otherThan(56f)), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateStringsOtherThanSpecified() {
    assertThat(anyStringOtherThan("56"), is(not(equalTo("56"))));
    assertThat(any(string(), otherThan("56")), is(not(equalTo("56"))));
  }

  @Test
  public void shouldGenerateShortsOtherThanSpecified() {
    assertThat(anyShortOtherThan((short)56), is(not(equalTo(56))));
    assertThat(any(shortValue(), otherThan((short) 56)), is(not(equalTo(56))));
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
  }

  //TODO add new Java 8 date time classes

  @Test
  public void shouldGenerateStringContainingGivenSubstring() {
    String str = anyStringContaining("1");

    assertThat(str, containsString("1"));
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
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
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

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(1)));
    assertThat(list, not(hasItem(2)));
    assertThat(list, not(hasItem(3)));
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
  public void shouldGenerateQueuesUsingClassSignature() {
    Queue<Integer> queue = manyAsQueueOf(intValues());

    assertThat(queue.size(), is(3));
    assertThat(queue, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(queue);
  }

  @Test
  public void shouldGenerateDequesUsingClassSignature() {
    Queue<Integer> collection = manyAsDequeOf(intValues());

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateSortedSetsUsingClassSignature() {
    SortedSet<Integer> collection = manyAsSortedSetOf(intValues());

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
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
    GenericInterface<GenericObject2<Integer>> o = any(new InstanceOf<GenericInterface<GenericObject2<Integer>>>() {});

    //THEN
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
  public static <T> SortedSet<T> manyAsSortedSetOf(Class<T> clazz) {
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
  public void shouldGenerateNumbers() {
    Integer anInt = anyInteger();
    Double aDouble = anyDouble();
    Float aFloat = anyFloat();
    Character aChar = anyChar();
    Character alphaChar = anyAlphaChar();
    Character aDigitChar = anyDigitChar();
    Long aLong = anyLong();
    Short aShort = anyShort();

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
  }

  @Test
  public void shouldGenerateExplodingInstanceOfInterfacesUsingInstanceSignature() {
    GenericInterface instance = anyExploding(new InstanceOf<GenericInterface>() {});

    assertThrows(BoomException.class, instance::getInstance);
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
