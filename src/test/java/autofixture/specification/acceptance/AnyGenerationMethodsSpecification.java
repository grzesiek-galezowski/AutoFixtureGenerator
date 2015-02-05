package autofixture.specification.acceptance;

import autofixture.publicinterface.BoomException;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.GenericInterface;
import autofixture.specification.acceptance.testfixtures.GenericObject;
import autofixture.specification.acceptance.testfixtures.NonGenericInterface;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static autofixture.publicinterface.Generate.any;
import static autofixture.publicinterface.Generate.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
    assertThat(anyDoubleOtherThan(56), is(not(equalTo(56))));
  }

  @Test
  public void shouldGenerateStringsOtherThanSpecified() {
    assertThat(anyStringOtherThan("56"), is(not(equalTo("56"))));
  }

  @Test
  public void shouldGenerateShortsOtherThanSpecified() {
    assertThat(anyShortOtherThan((short) 56), is(not(equalTo(56))));
  }



  /*
TODO
  public static <T> T anyOf(Class<T> enumClass) {
  public static Date anyDate() {
   */

  @Test
  public void shouldGenerateStringContainingGivenSubstring() {
    String str = anyStringContaining("1");

    assertThat(str, containsString("2"));
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
  }

  @Test
  public void shouldGenerateArraysExcludingGivenValues() {
    List<Integer> list = Arrays.asList(manyAsArrayOf(Integer.class, without(3, 5, 6, 7)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(3)));
    assertThat(list, not(hasItem(5)));
    assertThat(list, not(hasItem(6)));
    assertThat(list, not(hasItem(7)));
  }

  @Test
  public void shouldGenerateIterablesUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(Integer.class));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }

  @Test
  public void shouldGenerateIterablesUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(new InstanceOf<Integer>(){}));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }

  @Test
  public void shouldGenerateCollectionsUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(new InstanceOf<Integer>() {}));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }

  @Test
  public void shouldGenerateCollectionsUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsCollectionOf(Integer.class));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
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
  }


  @Test
  public void shouldGenerateIterablesWithoutSpecifiedValues() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(Integer.class, otherThan(1,2,3)));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
    assertThat(list, not(hasItem(1)));
    assertThat(list, not(hasItem(2)));
    assertThat(list, not(hasItem(3)));
  }


  @Test
  public void shouldGenerateListsUsingClassSignature() {
    List<Integer> list = manyAsListOf(Integer.class);

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }

  @Test
  public void shouldGenerateListsUsingInstanceSignature() {
    List<Integer> list = manyAsListOf(new InstanceOf<Integer>() {});

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
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
  }


  @Test
  public void shouldGenerateArraysUsingClassSignature() {
    List<Integer> list = Lists.newArrayList(manyAsArrayOf(Integer.class));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }

  @Test
  public void shouldGenerateArraysUsingInstanceSignature() {
    List<Integer> list = Lists.newArrayList(manyAsArrayOf(new InstanceOf<Integer>() {}));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }

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
