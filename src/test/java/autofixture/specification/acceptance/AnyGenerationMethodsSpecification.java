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
  }

  @Test
  public void shouldGenerateStringNotContainingGivenSubstring() {
    String str = anyStringNotContaining("1", "2");

    assertThat(str, not(containsString("1")));
    assertThat(str, not(containsString("2")));
  }

  @Test
  public void shouldGenerateStringOfGivenLength() {
    String str = anyStringOfLength(40);
    String str2 = anyStringOfLength(40);

    assertThat(str.length(), equalTo(40));
    assertThat(str2.length(), equalTo(40));
    assertThat(str, is(not(str2)));
  }

  @Test
  public void shouldGenerateAlphaStringOfGivenLength() {
    String str = anyAlphaString(40);
    String str2 = anyAlphaString(40);

    assertThat(str.length(), equalTo(40));
    assertThat(str2.length(), equalTo(40));
    assertThat(str, is(not(str2)));
  }

  @Test
  public void shouldGenerateLongsOtherThanSpecified() {
    assertThat(anyLongOtherThan(56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateIntegersOtherThanSpecified() {
    assertThat(anyIntegerOtherThan(56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateDoublesOtherThanSpecified() {
    assertThat(anyDoubleOtherThan(56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateFloatsOtherThanSpecified() {
    assertThat(anyFloatOtherThan(56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateStringsOtherThanSpecified() {
    assertThat(anyStringOtherThan("56"), is(not(equalTo("56"))));
  }

  @Test
  public void shouldGenerateShortsOtherThanSpecified() {
    assertThat(anyShortOtherThan((short) 56), is(not(equalTo(56))));
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

    Integer notA5 = any(new InstanceOf<Integer>() { }, otherThan(5, 10, 15));

    assertThat(notA5, is(not(equalTo(5))));
    assertThat(notA5, is(not(equalTo(10))));
    assertThat(notA5, is(not(equalTo(15))));
  }

  @Test
  public void shouldGenerateItemsOtherThanGivenValues() {

    Integer notA5 = any(Integer.class, otherThan(5, 10, 15));

    assertThat(notA5, is(not(equalTo(5))));
    assertThat(notA5, is(not(equalTo(10))));
    assertThat(notA5, is(not(equalTo(15))));
  }


  @Test
  public void shouldGenerateArraysExcludingGivenInstances() {
    List<Integer> list = Arrays.asList(manyAsArrayOf(new InstanceOf<Integer>() { }, without(3, 5, 6, 7)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateArraysExcludingGivenValues() {
    List<Integer> list = Arrays.asList(manyAsArrayOf(Integer.class, without(3, 5, 6, 7)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateIterablesUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(Integer.class));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateIterablesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(new InstanceOf<Integer>(){}));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(new InstanceOf<Integer>() {}));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(Integer.class));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsWithoutSpecifiedValuesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(new InstanceOf<Integer>() {
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
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(Integer.class, otherThan(3,5,6,7)));

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
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(Integer.class, otherThan(1,2,3)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(1)));
    assertThat(list, not(hasItem(2)));
    assertThat(list, not(hasItem(3)));
    assertContainsOnlyIntegers(list);
  }


  @Test
  public void shouldGenerateListsUsingClassSignature() {
    List<Integer> list = manyAsListOf(Integer.class);

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsUsingInstanceSignature() {
    List<Integer> list = manyAsListOf(new InstanceOf<Integer>() {});

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsWithoutSpecifiedItemsUsingInstanceSignature() {
    List<Integer> list = manyAsListOf(new InstanceOf<Integer>() {}, without(3,5,6,7));

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
    List<Integer> list = manyAsListOf(Integer.class, without(3,5,6,7));

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
    List<Integer> list = Lists.newArrayList(manyAsArrayOf(Integer.class));

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
    Set<Integer> list = manyAsSetOf(Integer.class);

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateQueuesUsingClassSignature() {
    Queue<Integer> queue = manyAsQueueOf(Integer.class);

    assertThat(queue.size(), is(3));
    assertThat(queue, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(queue);
  }

  @Test
  public void shouldGenerateDequesUsingClassSignature() {
    Queue<Integer> collection = manyAsDequeOf(Integer.class);

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateSortedSetsUsingClassSignature() {
    SortedSet<Integer> collection = manyAsSortedSetOf(Integer.class);

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateSortedMapsUsingClassSignature() {
    SortedMap<String, Integer> collection = manyAsSortedMapOf(String.class, Integer.class);

    assertThat(collection.size(), is(3));
    assertThat(collection.keySet(), not(hasItem(nullValue())));
    assertThat(collection.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection.values());
    assertContainsOnlyStrings(collection.keySet());
  }

  @Test
  public void shouldGenerateMapsUsingClassSignature() {
    Map<String, Integer> collection = manyAsMapOf(String.class, Integer.class);

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
    GenericObject2<GenericObject2<Integer>> o = any(new InstanceOf<GenericObject2<GenericObject2<Integer>>>() {});

    //THEN
    assertThat(o.field.field, instanceOf(Integer.class));
  }

  @Test
  public void shouldSupportNestedGenericInterfaceImplementations() {
    //GIVEN
    GenericInterface<GenericInterface<Integer>> o = any(new InstanceOf<GenericInterface<GenericInterface<Integer>>>() {});

    //THEN
    assertThat(o.getInstance(), instanceOf(GenericInterface.class));
    assertThat(o.getInstance().getInstance(), instanceOf(Integer.class));
  }


  private void assertContainsOnlyStrings(Collection<String> collection) {
    assertThat(collection.toArray()[0], instanceOf(String.class));
    assertThat(collection.toArray()[1], instanceOf(String.class));
    assertThat(collection.toArray()[2], instanceOf(String.class));
  }

  private void assertContainsOnlyIntegers(Collection<Integer> collection) {
    assertThat(collection.toArray()[0], instanceOf(Integer.class));
    assertThat(collection.toArray()[1], instanceOf(Integer.class));
    assertThat(collection.toArray()[2], instanceOf(Integer.class));
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
  }

  @Test
  public void lol() {
    createListOf(Integer.class);
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
