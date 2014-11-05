package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public class Jdk8TypesGenerationSpecification {
    private final Fixture fixture = new Fixture();

    @Theory
    public void shouldGenerateDifferentValuesEachTime(InstanceOf<?> instanceOfType) {
        Object o1 = fixture.create(instanceOfType);
        Object o2 = fixture.create(instanceOfType);

        assertThat(o1, not(nullValue()));
        assertThat(o2, not(nullValue()));
        assertThat(o1, not(o2));
        assertThat(o1, not(equalTo(o2)));
    }


    @DataPoint
    public static InstanceOf<Optional<Integer>> optionalWithIntClass = new InstanceOf<Optional<Integer>>() {};
    @DataPoint
    public static InstanceOf<OptionalInt> optionalIntClass = new InstanceOf<OptionalInt>() {};
    @DataPoint
    public static InstanceOf<OptionalDouble> optionalDoubleClass = new InstanceOf<OptionalDouble>() {};
    @DataPoint
    public static InstanceOf<OptionalLong> optionalLongClass = new InstanceOf<OptionalLong>() {};
    @DataPoint
    public static InstanceOf<Optional<OptionalLong>> optionalOfOptionalClass = new InstanceOf<Optional<OptionalLong>>() {};
    @DataPoint
    public static InstanceOf<BiConsumer<Integer, Float>> biConsumer = new InstanceOf<BiConsumer<Integer, Float>>() {};
    @DataPoint
    public static InstanceOf<BiPredicate<Integer, Float>> biPredicate = new InstanceOf<BiPredicate<Integer, Float>>() {};
    @DataPoint
    public static InstanceOf<BiFunction<Integer, Float, Integer>> biFunction = new InstanceOf<BiFunction<Integer, Float, Integer>>() {};
    @DataPoint
    public static InstanceOf<BinaryOperator<Boolean>> biOperator = new InstanceOf<BinaryOperator<Boolean>>() {};
    @DataPoint
    public static InstanceOf<LongToIntFunction> longToIntFunction = new InstanceOf<LongToIntFunction>() {};

}
