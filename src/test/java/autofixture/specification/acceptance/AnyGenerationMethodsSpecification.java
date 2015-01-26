package autofixture.specification.acceptance;

import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.GenericObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static autofixture.publicinterface.Generate.any;
import static autofixture.publicinterface.Generate.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AnyGenerationMethodsSpecification {

  @Test
  public void shouldGenerateEachTimeDifferentString() {
    String str1 = anyString();
    String str2 = anyString();

    assertThat(str1, is(not(str2)));
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
  public void shouldGenerateIterables() {
    List<Integer> list = Lists.newArrayList(manyAsIterableOf(Integer.class));

    assertThat(list.size(), is(3));
    assertThat(list, not(hasItem(nullValue())));
  }


}
