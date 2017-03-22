package autofixture.specification.unit;

import autofixture.implementationdetails.ConcreteInstanceType;
import autofixture.implementationdetails.MapBasedRecursionGuard;
import autofixture.interfaces.InstanceType;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.publicinterface.generators.ObjenesisGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by grzes on 20.03.2017.
 */
public class ObjenesisGeneratorSpecification {
    @Test
    public void shouldGenerateIntegers() {
        //GIVEN
        ObjenesisGenerator objenesisGenerator = new ObjenesisGenerator();
        InstanceType<Integer> instanceType = ConcreteInstanceType.fromClass(Integer.class);

        //WHEN
        Integer next = objenesisGenerator.next(instanceType, new Fixture());

        //THEN
        assertThat(next, is(not(nullValue())));
    }

    @Test
    public void shouldGenerateStrings() {
        //GIVEN
        ObjenesisGenerator objenesisGenerator = new ObjenesisGenerator();
        InstanceType<String> instanceType = ConcreteInstanceType.fromClass(String.class);

        //WHEN
        String next = objenesisGenerator.next(instanceType, new Fixture());

        //THEN
        assertThat(next, is(not(nullValue())));
    }

    @Test
    public void shouldGenerateDummyObjectsWithConstructors() {
        //GIVEN
        ObjenesisGenerator objenesisGenerator = new ObjenesisGenerator();
        InstanceType<MapBasedRecursionGuard> instanceType = ConcreteInstanceType.fromClass(MapBasedRecursionGuard.class);

        //WHEN
        MapBasedRecursionGuard next = objenesisGenerator.next(instanceType, new Fixture());

        //THEN
        assertThat(next, is(not(nullValue())));
    }

    @Test
    public void shouldGenerateGenericObjects() {
        //GIVEN
        ObjenesisGenerator objenesisGenerator = new ObjenesisGenerator();
        InstanceType<ArrayList<Integer>> instanceType = new ConcreteInstanceType(new InstanceOf<ArrayList<Integer>>() {});

        //WHEN
        ArrayList<Integer> next = objenesisGenerator.next(instanceType, new Fixture());

        //THEN
        assertThat(next, is(not(nullValue())));
    }


}
