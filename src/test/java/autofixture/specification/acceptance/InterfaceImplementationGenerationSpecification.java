package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.NonGenericInterface;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class InterfaceImplementationGenerationSpecification {
  Fixture fixture = new Fixture();

  @Test
  public void shouldBeAbleToGenerateInstanceOfSimpleInterfaceWithStableReturnValues() {
    NonGenericInterface implementation = fixture.create(NonGenericInterface.class);
    NonGenericInterface implementation2 = fixture.create(NonGenericInterface.class);

    implementation.doSomething();
    assertThat(implementation.getSomething(), is(not(0)));
    assertThat(implementation.getSomething(), is(implementation.getSomething()));
    assertThat(implementation2.getSomething(), is(not(0)));
    assertThat(implementation2.getSomething(), is(implementation2.getSomething()));
    assertThat(implementation.getSomething(), is(not(implementation2.getSomething())));
  }

  @Test
  public void shouldGenerateFiniteIterators() {
    @SuppressWarnings("serial")
    Iterator<String> it1 = fixture.create(new InstanceOf<Iterator<String>>() {
    });

    @SuppressWarnings("serial")
    Iterator<String> it2 = fixture.create(new InstanceOf<Iterator<String>>() {
    });

    assertThat(it1, is(notNullValue()));
    assertThat(it2, is(notNullValue()));
    assertThat(it1, not(is(it2)));
  }
}
