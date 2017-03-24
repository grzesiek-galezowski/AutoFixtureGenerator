package autofixture.specification.acceptance;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.matchers.HasArrayLengthMatcher;
import autofixture.specification.acceptance.matchers.HasArrayUniqueItemsMatcher;
import com.google.common.reflect.TypeToken;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import javax.management.openmbean.TabularDataSupport;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.script.SimpleBindings;
import javax.swing.*;
import javax.xml.ws.Provider;
import java.awt.*;
import java.security.AuthProvider;
import java.time.DayOfWeek;
import java.util.*;
import java.util.concurrent.*;
import java.util.jar.Attributes;

import static autofixture.publicinterface.Generate.any;
import static org.hamcrest.Matchers.hasSize;
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

    assertHasThreeUniqueEnumItems(new TypeToken<EnumMap<DayOfWeek, String>>() {}, collection);
  }


  @Theory
  public void shouldGenerateMapsWithThreeUniqueKeysAndValues(
      TypeToken<? extends Map<String, String>> collectionClass) {
    Map<String, String> collection = fixture.create(collectionClass);

    assertHasThreeUniqueItems(collectionClass, collection);
  }

  @Theory
  public void shouldGenerateMapsWithThreeUniqueElementsUsingAnyClass(
      TypeToken<? extends Map<String, String>> mapClass) {
    Map<String, String> collection = Any.anonymous(mapClass);

    assertHasThreeUniqueItems(mapClass, collection);
  }

  @Theory
  public void shouldGenerateMapsWithThreeUniqueElementsUsingAnyMethod(
      TypeToken<? extends Map<String, String>> mapClass) {
    Map<String, String> collection = any(mapClass);

    assertHasThreeUniqueItems(mapClass, collection);
  }

  private void assertHasThreeUniqueItems(
      TypeToken<? extends Map<String, String>> mapClass, Map<String, String> mapInstance) {
    assertTrue("Cannot assign " + mapInstance.getClass() + " to " + mapClass,
        mapClass.getRawType().isAssignableFrom(mapInstance.getClass()));

    assertThat(mapInstance.values().toArray(typeOfString()), this.<String>hasLength(3));
    assertThat(mapInstance.values().toArray(typeOfString()), this.<String>hasUniqueItems());
    assertThat(mapInstance.keySet().toArray(typeOfString()), this.<String>hasLength(3));
    assertThat(mapInstance.keySet().toArray(typeOfString()), this.<String>hasUniqueItems());

    System.out.println("OK");
  }

  private void assertHasThreeUniqueEnumItems(
      TypeToken<? extends Map<DayOfWeek, String>> mapClass, Map<DayOfWeek, String> mapInstance) {
    assertTrue("Cannot assign " + mapInstance.getClass() + " to " + mapClass,
        mapClass.getRawType().isAssignableFrom(mapInstance.getClass()));

    assertThat(mapInstance.values().toArray(typeOfString()), this.<String>hasLength(3));
    assertThat(mapInstance.values().toArray(typeOfString()), this.<String>hasUniqueItems());
    assertThat(mapInstance.keySet().toArray(typeOfDayOfWeek()), this.<DayOfWeek>hasLength(3));
    assertThat(mapInstance.keySet().toArray(typeOfDayOfWeek()), this.<DayOfWeek>hasUniqueItems());

    System.out.println("OK");
  }

  private DayOfWeek[] typeOfDayOfWeek() {
    return new DayOfWeek[]{};
  }

  private String[] typeOfString() {
    return new String[]{};
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private <T> Matcher<? super T[]> hasUniqueItems() {
    return new HasArrayUniqueItemsMatcher();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private <T> Matcher<? super T[]> hasLength(int i) {
    return new HasArrayLengthMatcher(i);
  }

}


