package autofixture.specification.acceptance;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.matchers.ArrayMatchers;
import com.google.common.reflect.TypeToken;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static autofixture.publicinterface.Generate.any;
import static autofixture.specification.acceptance.matchers.ArrayMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by grzes on 23.03.2017.
 */
@RunWith(Theories.class)
public class MapTypesGenerationSpecification {

  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<Map<String, String>> mapClass = new TypeToken<Map<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<SortedMap<String, String>> sortedMapClass = new TypeToken<SortedMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<TreeMap<String, String>> treeMapClass = new TypeToken<TreeMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<NavigableMap<String, String>> navigableMapClass = new TypeToken<NavigableMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<AbstractMap<String, String>> abstractMapClass = new TypeToken<AbstractMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<ConcurrentHashMap<String, String>> concurrentHashMapClass = new TypeToken<ConcurrentHashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<ConcurrentSkipListMap<String, String>> concurrentSkipListMapClass = new TypeToken<ConcurrentSkipListMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<HashMap<String, String>> hashMapClass = new TypeToken<HashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<Hashtable<String, String>> hashtableClass = new TypeToken<Hashtable<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<IdentityHashMap<String, String>> identityHashMapClass = new TypeToken<IdentityHashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<LinkedHashMap<String, String>> linkedHashMapClass = new TypeToken<LinkedHashMap<String, String>>() {
  };
  @SuppressWarnings("serial")
  @DataPoint
  public static TypeToken<WeakHashMap<String, String>> weakHashMapClass = new TypeToken<WeakHashMap<String, String>>() {
  };


  Fixture fixture = new Fixture();

  @Theory
  public void shouldGenerateEnumMaps() {
    Map<DayOfWeek, String> collection = fixture.create(new InstanceOf<EnumMap<DayOfWeek, String>>() {});

    assertHasThreeUniqueEnumItems(
        new TypeToken<EnumMap<DayOfWeek, String>>() {},
        collection,
        DayOfWeek.class,
        String.class);
  }


  @Theory
  public void shouldGenerateMapsWithThreeUniqueKeysAndValues(
      TypeToken<? extends Map<String, String>> collectionClass) {
    Map<String, String> collection = fixture.create(collectionClass);

    assertHasThreeUniqueEnumItems(
        collectionClass, collection, String.class, String.class);
  }

  @Theory
  public void shouldGenerateMapsWithThreeUniqueElementsUsingAnyClass(
      TypeToken<? extends Map<String, String>> mapClass) {
    Map<String, String> collection = Any.anonymous(mapClass);

    assertHasThreeUniqueEnumItems(
        mapClass, collection, String.class, String.class);
  }

  @Theory
  public void shouldGenerateMapsWithThreeUniqueElementsUsingAnyMethod(
      TypeToken<? extends Map<String, String>> mapClass) {
    Map<String, String> collection = any(mapClass);

    assertHasThreeUniqueEnumItems(
        mapClass,
        collection,
        String.class,
        String.class);
  }

  private static <T, U> void assertHasThreeUniqueEnumItems(
      TypeToken<? extends Map<T, U>> mapClass,
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


