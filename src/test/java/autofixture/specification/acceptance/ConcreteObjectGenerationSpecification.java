package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.specification.acceptance.matchers.HasArrayLengthMatcher;
import autofixture.specification.acceptance.testfixtures.*;
import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ConcreteObjectGenerationSpecification {

  Fixture fixture = new Fixture();
  HashSet<Double>[] doubleSets = array(new HashSet<Double>());

  @SafeVarargs
  static <E> E[] array(E... array) {
    return array;
  }

  @Test
  public void shouldCreateFilledObjectWithPrimitiveValuesInConstructor() {
    ObjectWithPrimitiveConstructorParameters obj1
      = fixture.create(TypeToken.of(ObjectWithPrimitiveConstructorParameters.class));
    ObjectWithPrimitiveConstructorParameters obj2
      = fixture.create(TypeToken.of(ObjectWithPrimitiveConstructorParameters.class));

    assertThat(obj1.intParameter, is(not(0)));
    assertThat(obj2.intParameter, is(not(0)));

    assertThat(obj1.doubleParameter, is(not(0.0)));
    assertThat(obj2.doubleParameter, is(not(0.0)));

    assertThat(obj1.stringParameter, is(notNullValue()));
    assertThat(obj2.stringParameter, is(notNullValue()));

    assertThat(obj1.stringParameter, is(not("")));
    assertThat(obj2.stringParameter, is(not("")));

    assertThat(obj1.intParameter, is(not(obj2.intParameter)));
    assertThat(obj1.doubleParameter, is(not(obj2.doubleParameter)));
    assertThat(obj1.stringParameter, is(not(obj2.stringParameter)));
  }

  @Test
  public void shouldCreateFilledObjectWithCollectionParametersInConstructor() {
    ObjectWithCollectionConstructorParameters obj1
      = fixture.create(TypeToken.of(ObjectWithCollectionConstructorParameters.class));

    assertThat(obj1.intListParameter.toArray(), hasManyItems());

    assertThat(obj1.doubleSetSetParameter.toArray(), hasManyItems());

    Object[] nestedSet1Obj1 = obj1.doubleSetSetParameter.toArray(doubleSets)[0].toArray();
    Object[] nestedSet2Obj1 = obj1.doubleSetSetParameter.toArray(doubleSets)[1].toArray();
    Object[] nestedSet3Obj1 = obj1.doubleSetSetParameter.toArray(doubleSets)[2].toArray();

    assertThat(nestedSet1Obj1, hasManyItems());
    assertThat(nestedSet2Obj1, hasManyItems());
    assertThat(nestedSet3Obj1, hasManyItems());

    assertThat(obj1.stringArrayParameter, hasManyItems());
    assertThat(obj1.stringArrayParameter[0], hasManyItems());
    assertThat(obj1.stringArrayParameter[1], hasManyItems());
    assertThat(obj1.stringArrayParameter[2], hasManyItems());
  }

  @Test
  public void shouldSetPropertiesOfCreatedConcreteObjectUsingSetters() {
    ObjectWithSetters instance = fixture.create(ObjectWithSetters.class);

    assertThat(instance, is(notNullValue()));
    assertThat(instance.getAge(), is(not(0)));
    assertThat(instance.getName(), is(notNullValue()));
    assertThat(instance.getName(), is(not("")));
  }

  private HasArrayLengthMatcher<Object> hasManyItems() {
    return new HasArrayLengthMatcher<>(3);
  }

  @Test
  public void shouldCreateObjectsWithPackagePrivateConstructors() {
    ObjectWithPackagePrivateConstructor o1 = fixture.create(ObjectWithPackagePrivateConstructor.class);
    ObjectWithPackagePrivateConstructor o2 = fixture.create(ObjectWithPackagePrivateConstructor.class);

    assertThat(o1.getX(), not(equalTo(o2.getX())));
  }

  @Test
  public void shouldPreferPublicConstructorsOverPackagePrivateConstructors() {
    ObjectWithPackagePrivateAndPublicConstructor o1
        = fixture.create(ObjectWithPackagePrivateAndPublicConstructor.class);
    assertThat(o1.getStr(), equalTo("Hello"));
  }

  @Test
  public void shouldCreateInstancesOfClassesWithCustomGenericConstructorParameters() {
    ObjectWithGenericConstructorParameters obj
      = fixture.create(TypeToken.of(ObjectWithGenericConstructorParameters.class));

    assertThat(obj.parameter1.getInstance(), is(notNullValue()));
    assertThat(obj.parameter1.getInstance(), is(not("")));
  }

  @Test
  public void shouldPickSingleParameterConstructorOverParameterlessOneWhenBothExist() {
    ValueObjectWithSingleParameterConstructorAndParameterlessOne obj1
      = fixture.create(ValueObjectWithSingleParameterConstructorAndParameterlessOne.class);

    ValueObjectWithSingleParameterConstructorAndParameterlessOne obj2
      = fixture.create(ValueObjectWithSingleParameterConstructorAndParameterlessOne.class);

    assertThat(obj1, is(not(equalTo(obj2))));
  }

  @Test
  public void shouldSuccessfullyGenerateObjectsWithPublicFinalFields() {
    ObjectWithPublicFinalFields o = fixture.create(ObjectWithPublicFinalFields.class);
    assertThat(o, is(notNullValue()));
    assertThat(o.age, is(not(nullValue())));
  }

}
