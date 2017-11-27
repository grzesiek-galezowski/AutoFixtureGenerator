package autofixture.specification.acceptance;

import autofixture.exceptions.BoomException;
import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.publicinterface.thirdpartysupport.AnyVavr;
import autofixture.specification.acceptance.testfixtures.*;
import com.google.common.collect.Lists;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.DayOfWeek;
import java.util.*;

import static autofixture.publicinterface.InlineGenerators.*;
import static autofixture.specification.acceptance.CustomAssertions.*;
import static autofixture.specification.acceptance.TypeHelpers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
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
    return Arrays.asList(new Object[50][0]);
  }

  public AnyGenerationMethodsSpecification() {
  }


  @Test
  public void shouldGenerateEachTimeDifferentString() {
    String str1 = Any.string();
    String str2 = Any.string();

    assertThat(str1, is(not(str2)));
  }

  @Test
  public void shouldGenerateEachTimeDifferentAlphaString() {
    String str1 = Any.alphaString();
    String str2 = Any.alphaString();

    assertThat(str1, is(not(str2)));

    String str3 = Any.anonymous(alphaString());
    String str4 = Any.anonymous(alphaString());

    assertThat(str3, is(not(str4)));
  }

  @Test
  public void shouldGenerateStringNotContainingGivenSubstring() {
    String string1 = "1";
    String string2 = "2";
    String str = Any.stringNotContaining(string1, string2);

    assertThat(str, not(containsString(string1)));
    assertThat(str, not(containsString(string2)));

    String str2 = Any.anonymous(stringNotContaining(string1, string2));

    assertThat(str2, not(containsString(string1)));
    assertThat(str2, not(containsString(string2)));
  }

  @Test
  public void shouldGenerateStringOfGivenLength() {

    int size = 40;
    AssertTwoStringsWithExpectedSizeAreDifferent(
        Any.stringOfLength(size),
        Any.stringOfLength(size),
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
        Any.alphaString(size),
        Any.alphaString(size),
        size);

    AssertTwoStringsWithExpectedSizeAreDifferent(
        Any.anonymous(alphaString(size)),
        Any.anonymous(alphaString(size)),
        size);
  }

  private void AssertTwoStringsWithExpectedSizeAreDifferent(String str, String str2, int expectedSize) {
    assertThat(str.length(), equalTo(expectedSize));
    assertThat(str2.length(), equalTo(expectedSize));
    assertThat(str, is(not(str2)));
  }

  @Test
  public void shouldGenerateLowercaseStrings() {
    String lowercaseString1 = Any.lowercaseString();
    String lowercaseString2 = Any.anonymous(lowercaseString());
    String lowercaseString3 = Any.anonymous(lowercaseString());
    String lowercaseString4 = Any.lowercaseString();

    assertThat(lowercaseString1, is(lowercaseString1.toLowerCase()));
    assertThat(lowercaseString2, is(lowercaseString2.toLowerCase()));
    assertThat(lowercaseString3, is(lowercaseString3.toLowerCase()));
    assertThat(lowercaseString4, is(lowercaseString4.toLowerCase()));
  }

  @Test
  public void shouldGenerateUppercaseStrings() {
    String upperString1 = Any.uppercaseString();
    String upperString2 = Any.anonymous(uppercaseString());
    String upperString3 = Any.anonymous(uppercaseString());
    String upperString4 = Any.uppercaseString();

    assertThat(upperString1, is(upperString1.toUpperCase()));
    assertThat(upperString2, is(upperString2.toUpperCase()));
    assertThat(upperString3, is(upperString3.toUpperCase()));
    assertThat(upperString4, is(upperString4.toUpperCase()));
  }


  @Test
  public void shouldGenerateLongsOtherThanSpecified() {
    int int1 = 56;
    long long1 = 56l;
    long long2 = 55l;
    assertThat(Any.anonymous(longValue(), otherThan(long1, long2)), is(not(equalTo(int1))));
    assertThat(Any.longOtherThan(long1, long2), is(not(equalTo(int1))));
  }

  @Test
  public void shouldGenerateIntegersOtherThanSpecified() {
    int longNum = 56;
    assertThat(Any.intOtherThan(longNum), is(not(equalTo(longNum))));
    assertThat(Any.anonymous(intValue(), otherThan(longNum)), is(not(equalTo(longNum))));
  }

  @Test
  public void shouldGenerateDoublesOtherThanSpecified() {
    double doubleNum = 56d;
    assertThat(Any.anonymous(doubleValue(), otherThan(doubleNum)), is(not(equalTo(doubleNum))));
    assertThat(Any.doubleOtherThan(doubleNum), is(not(equalTo(doubleNum))));
    assertThat(Any.otherThan(doubleNum), is(not(equalTo(doubleNum))));
  }


  @Test
  public void shouldCorrectlyGenerateConstrainedArrays() {
    int someNumber = 123;
    Integer[] integers = Any.arrayOf(Integer.class, otherThan(someNumber));

    GenericObject<Integer>[] objects = Any.arrayOf(
        new InstanceOf<GenericObject<Integer>>() {
        }, otherThan(new GenericObject<>(someNumber)));

    assertThat(integers, is(not(nullValue())));
    assertThat(objects, is(not(nullValue())));
  }

  @Test
  public void shouldGeneratePlainObjects() {
    val o1 = Any.anonymous(Object.class);
    val o2 = Any.anonymous(Object.class);
    assertThat(o1, is(not(equalTo(o2))));
    assertThat(o1.getClass(), is(equalTo(Object.class)));
  }

  @Test
  public void shouldGenerateFloatsOtherThanSpecified() {
    float floatNum = 56f;
    assertThat(Any.anonymous(floatValue(), otherThan(floatNum)), is(not(equalTo(floatNum))));
    assertThat(Any.floatOtherThan(floatNum), is(not(equalTo(floatNum))));
  }

  @Test
  public void shouldGenerateStringsOtherThanSpecified() {
    String someString = "56";
    assertThat(Any.anonymous(string(), otherThan(someString)), is(not(equalTo(someString))));
    assertThat(Any.stringOtherThan(someString), is(not(equalTo(someString))));
  }

  @Test
  public void shouldGenerateShortsOtherThanSpecified() {
    short shortNum = (short) 56;
    assertThat(Any.anonymous(shortValue(), otherThan(shortNum)), is(not(equalTo(shortNum))));
    assertThat(Any.shortOtherThan(shortNum), is(not(equalTo(shortNum))));
  }

  @Test
  public void shouldGenerateDistinctEnumValues() {
    DayOfWeek dow1 = Any.anonymous(DayOfWeek.class);
    DayOfWeek dow2 = Any.anonymous(DayOfWeek.class);

    assertThat(dow1, is(not(nullValue())));
    assertThat(dow2, is(not(nullValue())));
    assertThat(dow1, is(not(equalTo(dow2))));
  }

  @Test
  public void shouldGenerateDistinctDates() {
    Date date1 = Any.anonymous(Date.class);
    Date date2 = Any.anonymous(Date.class);

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
    String someString = "1";
    String str1 = Any.stringContaining(someString);
    assertThat(str1, containsString(someString));

    String str2 = Any.anonymous(stringContaining(someString));
    assertThat(str2, containsString(someString));

    String str3 = Any.stringContaining(someString);
    assertThat(str3, containsString(someString));
  }

  @Test
  public void shouldGenerateEachTimeDifferentInstance() {
    GenericObject<Integer> o1 = Any.anonymous(new InstanceOf<GenericObject<Integer>>() {
    });
    GenericObject<Integer> o2 = Any.anonymous(new InstanceOf<GenericObject<Integer>>() {
    });
    assertThat(o1, is(not(o2)));
  }

  @Test
  public void shouldGenerateItemsOtherThanGivenInstances() {
    int num1 = 5;
    int num2 = 10;
    int num3 = 15;
    Integer notA5 = Any.anonymous(new InstanceOf<Integer>() {
    }, otherThan(num1, num2, num3));

    assertThat(notA5, is(not(equalTo(num1))));
    assertThat(notA5, is(not(equalTo(num2))));
    assertThat(notA5, is(not(equalTo(num3))));
  }

  @Test
  public void shouldGenerateItemsOtherThanGivenValues() {

    int num1 = 5;
    int num2 = 10;
    int num3 = 15;
    Integer notA5 = Any.otherThan(num1, num2, num3) ;

    assertThat(notA5, is(not(equalTo(num1))));
    assertThat(notA5, is(not(equalTo(num2))));
    assertThat(notA5, is(not(equalTo(num3))));
  }


  @Test
  public void shouldGenerateArraysExcludingGivenInstances() {
    int num1 = 3;
    int num2 = 5;
    int num3 = 6;
    int num4 = 7;
    List<Integer> list = Arrays.asList(Any.arrayOf(new InstanceOf<Integer>() {
    }, without(num1, num2, num3, num4)));

    assertThat(list.size(), is(num1));
    assertThat(list, not(hasItem(num1)));
    assertThat(list, not(hasItem(num2)));
    assertThat(list, not(hasItem(num3)));
    assertThat(list, not(hasItem(num4)));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateArraysExcludingGivenValues() {
    List<Integer> list = Arrays.asList(Any.arrayOf(intValues(), without(3, 5, 6, 7)));

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
    List<Integer> list = Lists.newArrayList(Any.iterableOf(intValues()));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateIterablesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(
        Any.iterableOf(new InstanceOf<Integer>() {
    }));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(Any.collectionOf(new InstanceOf<Integer>() {
    }));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(Any.collectionOf(intValues()));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateCollectionsWithoutSpecifiedValuesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(Any.collectionOf(
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
    List<Integer> list = Lists.newArrayList(Any.collectionOf(intValues(), without(3, 5, 6, 7)));

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
    List<Integer> list = Lists.newArrayList(Any.iterableOf(intValues(), without(1, 2, 3)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(1)));
    assertThat(list, not(hasItem(2)));
    assertThat(list, not(hasItem(3)));
    assertContainsOnlyIntegers(list);
  }


  @Test
  public void shouldGenerateListsUsingClassSignature() {
    List<Integer> list = Any.listOf(intValues());

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsUsingInstanceSignature() {
    List<Integer> list = Any.listOf(new InstanceOf<Integer>() {
    });

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateListsWithoutSpecifiedItemsUsingInstanceSignature() {
    List<Integer> list = Any.listOf(new InstanceOf<Integer>() {
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
    List<Integer> list = Any.listOf(intValues(), without(3, 5, 6, 7));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
    assertContainsOnlyIntegers(list);
  }



  @Test
  public void shouldGenerateImmutableListsUsingClassSignature() {
    final io.vavr.collection.List<Integer> list = AnyVavr.listOf(intValues());

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list.toJavaList());

    Fixture fixture = new Fixture();
    val list2 = fixture.create(new InstanceOf<io.vavr.collection.List<Integer>>(){});
    assertThat(list2.size(), is(3));
    assertThat(list2, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list2.toJavaList());

    io.vavr.collection.Map<String, Integer> map = fixture.create(new InstanceOf<io.vavr.collection.Map<String, Integer>>() {});
    assertThat(map.size(), is(3));
    assertThat(map.keySet(), not(hasItem(nullValue())));
    assertThat(map.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(map.toJavaMap().values());
    assertContainsOnlyStrings(map.toJavaMap().keySet());


    fixture.create(new InstanceOf<io.vavr.collection.Traversable<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.List<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Seq<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.LinearSeq<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.IndexedSeq<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Ordered<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.HashMap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Array<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.HashSet<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Set<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.SortedMap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.SortedSet<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Tree<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.CharSeq>(){});
    fixture.create(new InstanceOf<CharSequence>(){}); //todo add canonical way - this is java.util
    fixture.create(new InstanceOf<io.vavr.collection.Vector<Integer>>(){});

    fixture.create(new InstanceOf<io.vavr.collection.LinkedHashMap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.LinkedHashSet<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.PriorityQueue<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Queue<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.TreeMap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.TreeMultimap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.TreeSet<Integer>>(){});

    fixture.create(new InstanceOf<io.vavr.collection.SortedMultimap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Multimap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.HashMultimap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.LinkedHashMultimap<Integer, Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.Iterator<Integer>>(){});
    fixture.create(new InstanceOf<io.vavr.collection.BitSet<Integer>>(){});
  }



  @Test
  public void shouldGenerateArraysUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(Any.arrayOf(intValues()));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateArraysUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(Any.arrayOf(new InstanceOf<Integer>() {
    }));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateSetsUsingClassSignature() {
    Set<Integer> list = Any.setOf(intValues());

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(list);
  }

  @Test
  public void shouldGenerateSetsWithoutSpecifiedItemsUsingClassSignature() {
    Set<Integer> list = Any.setOf(intValues(), without(3, 5, 6, 7));

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
    Queue<Integer> queue = Any.queueOf(intValues());

    assertThat(queue.size(), is(3));
    assertThat(queue, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(queue);
  }

  @Test
  public void shouldGenerateQueuesWithoutSpecifiedItemsUsingClassSignature() {
    Queue<Integer> list = Any.queueOf(intValues(), without(3, 5, 6, 7));

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
    Queue<Integer> collection = Any.dequeOf(intValues());

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateDequesWithoutSpecifiedItemsUsingClassSignature() {
    Deque<Integer> list = Any.dequeOf(intValues(), without(3, 5, 6, 7));

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
    SortedSet<Integer> collection = Any.sortedSetOf(intValues());

    assertThat(collection.size(), is(3));
    assertThat(collection, not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection);
  }

  @Test
  public void shouldGenerateSortedSetsWithoutSpecifiedItemsUsingClassSignature() {
    SortedSet<Integer> list = Any.sortedSetOf(intValues(), without(3, 5, 6, 7));

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
    SortedMap<String, Integer> collection = Any.sortedMapBetween(string(), intValue());

    assertThat(collection.size(), is(3));
    assertThat(collection.keySet(), not(hasItem(nullValue())));
    assertThat(collection.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection.values());
    assertContainsOnlyStrings(collection.keySet());
  }

  @Test
  public void shouldGenerateMapsUsingClassSignature() {
    Map<String, Integer> collection = Any.mapBetween(string(), intValue());

    assertThat(collection.size(), is(3));
    assertThat(collection.keySet(), not(hasItem(nullValue())));
    assertThat(collection.values(), not(hasItem(nullValue())));
    assertContainsOnlyIntegers(collection.values());
    assertContainsOnlyStrings(collection.keySet());
  }

  @Test
  public void shouldGenerateAnIntegerInstanceFromProvided() {
    int chosen1 = Any.from(1, 3, 4, 6, 7, 8);
    int chosen2 = Any.from(1, 3, 4, 6, 7, 8);
    int chosen3 = Any.from(1, 3, 4, 6, 7, 8);
    int chosen4 = Any.from(1, 3, 4, 6, 7, 8);
    int chosen5 = Any.from(1, 3, 4, 6, 7, 8);
    int chosen6 = Any.from(1, 3, 4, 6, 7, 8);

    List<Integer> allResults = Arrays.asList(
        chosen1, chosen2, chosen3, chosen4, chosen5, chosen6);
    assertThat(allResults, hasItem(1));
    assertThat(allResults, hasItem(3));
    assertThat(allResults, hasItem(4));
    assertThat(allResults, hasItem(6));
    assertThat(allResults, hasItem(7));
    assertThat(allResults, hasItem(8));
  }

  @Test
  public void shouldSupportNestedGenericImplementations() {
    //GIVEN
    GenericObject<GenericObject<Integer>> o = Any.anonymous(new InstanceOf<GenericObject<GenericObject<Integer>>>() {
    });

    //THEN
    assertThat(o.getInstance(), instanceOf(GenericObject.class));
    assertThat(o.getInstance().getInstance(), instanceOf(intValue()));
  }

  @Test
  public void shouldSupportNestedGenericInterfaceImplementations() {
    //GIVEN
    GenericInterface<GenericObject2<Integer>> o =
        Any.anonymous(new InstanceOf<GenericInterface<GenericObject2<Integer>>>() {
        });

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
        Any.intValue(),
        Any.doubleValue(),
        Any.floatValue(),
        Any.charValue(),
        Any.alphaChar(),
        Any.digitChar(),
        Any.longValue(),
        Any.shortValue());
  }

  @Test
  public void shouldGenerateNumbersUsingCompositionalApi() {

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
    NonGenericInterface instance2 = Any.exploding(NonGenericInterface.class);

    assertThrows(BoomException.class, instance2::doSomething);
    assertThrows(BoomException.class, instance2::getSomething);

  }

  @Test
  public void shouldGenerateExplodingInstanceOfInterfacesUsingInstanceSignature() {
    GenericInterface instance2 = Any.exploding(new InstanceOf<GenericInterface>() {});
    assertThrows(BoomException.class, instance2::getInstance);
  }

  @Test
  public void shouldCreateDifferentPortEachTime() {
    int port1 = Any.port();
    int port2 = Any.port();

    assertThat(port1, is(not(equalTo(port2))));
    assertThat(port1, is(lessThan(65535)));
    assertThat(port2, is(lessThan(65535)));

    int port3 = Any.anonymous(portNumber());
    int port4 = Any.anonymous(portNumber());

    assertThat(port3, is(not(equalTo(port4))));
    assertThat(port3, is(lessThan(65535)));
    assertThat(port4, is(lessThan(65535)));
  }

  @Test
  public void shouldAvoidCopyConstructorIfPossible() {
    StructureWithCopyConstructor anonymous
        = Any.anonymous(StructureWithCopyConstructor.class);
    assertThat(anonymous.getX(), is(notNullValue()));
    assertThat(anonymous.getY(), is(notNullValue()));
  }

  @Test
  public void shouldAvoidCopyConstructorIfPossible123() {
    assertThrows(RuntimeException.class,
        () -> Any.otherThan(new ArrayList<Integer>()));
  }


}
