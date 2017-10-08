package autofixture.specification.acceptance;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.matchers.ArrayMatchers;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static autofixture.specification.acceptance.matchers.ArrayMatchers.typeOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by grzes on 23.03.2017.
 */
@RunWith(Theories.class)
public class MapTypesGenerationSpecification {

  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Map<String, String>> mapClass = new InstanceOf<Map<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<SortedMap<String, String>> sortedMapClass = new InstanceOf<SortedMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<TreeMap<String, String>> treeMapClass = new InstanceOf<TreeMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<NavigableMap<String, String>> navigableMapClass = new InstanceOf<NavigableMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<AbstractMap<String, String>> abstractMapClass = new InstanceOf<AbstractMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ConcurrentHashMap<String, String>> concurrentHashMapClass = new InstanceOf<ConcurrentHashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<ConcurrentSkipListMap<String, String>> concurrentSkipListMapClass = new InstanceOf<ConcurrentSkipListMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<HashMap<String, String>> hashMapClass = new InstanceOf<HashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<Hashtable<String, String>> hashtableClass = new InstanceOf<Hashtable<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<IdentityHashMap<String, String>> identityHashMapClass = new InstanceOf<IdentityHashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<LinkedHashMap<String, String>> linkedHashMapClass = new InstanceOf<LinkedHashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static InstanceOf<WeakHashMap<String, String>> weakHashMapClass = new InstanceOf<WeakHashMap<String, String>>() {
  };


  Fixture fixture = new Fixture();

  @Theory
  public void shouldGenerateEnumMaps() {
    Map<DayOfWeek, String> collection = fixture.create(new InstanceOf<EnumMap<DayOfWeek, String>>() {});

    assertHasThreeUniqueEnumItems(
        new InstanceOf<EnumMap<DayOfWeek, String>>() {},
        collection,
        DayOfWeek.class,
        String.class);
  }


  @Theory
  public void shouldGenerateMapsWithThreeUniqueKeysAndValues(
      InstanceOf<? extends Map<String, String>> collectionClass) {
    Map<String, String> collection = fixture.create(collectionClass);

    assertHasThreeUniqueEnumItems(
        collectionClass, collection, String.class, String.class);
  }

  @Theory
  public void shouldGenerateMapsWithThreeUniqueElementsUsingAnyClass(
      InstanceOf<? extends Map<String, String>> mapClass) {
    Map<String, String> collection = Any.anonymous(mapClass);

    assertHasThreeUniqueEnumItems(
        mapClass, collection, String.class, String.class);
  }

  @Theory
  public void shouldGenerateMapsWithThreeUniqueElementsUsingAnyMethod(
      InstanceOf<? extends Map<String, String>> mapClass) {
    Map<String, String> collection = Any.anonymous(mapClass);

    assertHasThreeUniqueEnumItems(
        mapClass,
        collection,
        String.class,
        String.class);
  }

  private static <T, U> void assertHasThreeUniqueEnumItems(
      InstanceOf<? extends Map<T, U>> mapClass,
      Map<T, U> mapInstance,
      Class<T> keyClass, Class<U> valueClass) {

    assertTrue("Cannot assign " + mapInstance.getClass() + " to " + mapClass,
        mapClass.getRawType().isAssignableFrom(mapInstance.getClass()));

    assertThat(mapInstance.keySet().toArray(typeOf(keyClass)), ArrayMatchers.<T>hasLength(3));
    assertThat(mapInstance.keySet().toArray(typeOf(keyClass)), ArrayMatchers.<T>hasUniqueItems());
    assertThat(mapInstance.values().toArray(typeOf(valueClass)), ArrayMatchers.<U>hasLength(3));
    assertThat(mapInstance.values().toArray(typeOf(valueClass)), ArrayMatchers.<U>hasUniqueItems());

    System.out.println("OK");
  }


}


