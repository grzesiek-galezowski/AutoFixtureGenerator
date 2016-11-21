package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.*;
import java.time.chrono.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public class Jdk8TypesGenerationSpecification {

  /*
   Java 8 Optionals
   */
  @DataPoint public static InstanceOf<Optional<Integer>> optionalWithIntClass = new InstanceOf<Optional<Integer>>() {  };
  @DataPoint public static InstanceOf<OptionalInt> optionalIntClass = new InstanceOf<OptionalInt>() {  };
  @DataPoint public static InstanceOf<OptionalDouble> optionalDoubleClass = new InstanceOf<OptionalDouble>() {  };
  @DataPoint public static InstanceOf<OptionalLong> optionalLongClass = new InstanceOf<OptionalLong>() {  };
  @DataPoint public static InstanceOf<Optional<OptionalLong>> optionalOfOptionalClass = new InstanceOf<Optional<OptionalLong>>() {  };

  /*
   Some Java 8 functional interfaces
   */
  @DataPoint public static InstanceOf<Consumer<Integer>> consumer = new InstanceOf<Consumer<Integer>>() {  };
  @DataPoint public static InstanceOf<Supplier<Integer>> supplier = new InstanceOf<Supplier<Integer>>() {  };
  @DataPoint public static InstanceOf<BiConsumer<Integer, Float>> biConsumer = new InstanceOf<BiConsumer<Integer, Float>>() {  };
  @DataPoint public static InstanceOf<BiPredicate<Integer, Float>> biPredicate = new InstanceOf<BiPredicate<Integer, Float>>() {  };
  @DataPoint public static InstanceOf<BiFunction<Integer, Float, Integer>> biFunction = new InstanceOf<BiFunction<Integer, Float, Integer>>() {  };
  @DataPoint public static InstanceOf<BinaryOperator<Boolean>> biOperator = new InstanceOf<BinaryOperator<Boolean>>() {  };
  @DataPoint public static InstanceOf<LongToIntFunction> longToIntFunction = new InstanceOf<LongToIntFunction>() {  };
  @DataPoint public static InstanceOf<Runnable> runnable = new InstanceOf<Runnable>() {  };

  /*
   Date Time library types
   */
  @DataPoint public static InstanceOf<LocalDateTime> localDateTime = new InstanceOf<LocalDateTime>() {  };
  @DataPoint public static InstanceOf<LocalDate> localDate = new InstanceOf<LocalDate>() {  };
  @DataPoint public static InstanceOf<Month> month = new InstanceOf<Month>() {  };
  @DataPoint public static InstanceOf<ZonedDateTime> zonedDateTime = new InstanceOf<ZonedDateTime>() {  };
  @DataPoint public static InstanceOf<ZoneId> zoneId = new InstanceOf<ZoneId>() {  };
  @DataPoint public static InstanceOf<OffsetTime> offsetTime = new InstanceOf<OffsetTime>() {  };
  @DataPoint public static InstanceOf<Period> period = new InstanceOf<Period>() {  };
  @DataPoint public static InstanceOf<ChronoPeriod> chronoPeriod = new InstanceOf<ChronoPeriod>() {  };
  @DataPoint public static InstanceOf<Duration> duration = new InstanceOf<Duration>() {  };
  @DataPoint public static InstanceOf<Chronology> chronology = new InstanceOf<Chronology>() {  };
  @DataPoint public static InstanceOf<ChronoLocalDate> chronoLocalDate = new InstanceOf<ChronoLocalDate>() {  };
  @DataPoint public static InstanceOf<ChronoLocalDateTime> chronoLocalDateTime = new InstanceOf<ChronoLocalDateTime>() {  };
  @DataPoint public static InstanceOf<ChronoZonedDateTime> chronoZonedDateTime = new InstanceOf<ChronoZonedDateTime>() {  };
  @DataPoint public static InstanceOf<ZoneOffset> zoneOffset = new InstanceOf<ZoneOffset>() {  };
  @DataPoint public static InstanceOf<Clock> clock = new InstanceOf<Clock>() {  };
  @DataPoint public static InstanceOf<Instant> instant = new InstanceOf<Instant>() {  };
  @DataPoint public static InstanceOf<TemporalUnit> temporalUnit = new InstanceOf<TemporalUnit>() {  };
  @DataPoint public static InstanceOf<Temporal> temporal = new InstanceOf<Temporal>() {  };
  @DataPoint public static InstanceOf<TemporalAccessor> temporalAccessor = new InstanceOf<TemporalAccessor>() {  };
  @DataPoint public static InstanceOf<TemporalAdjuster> temporalAdjuster = new InstanceOf<TemporalAdjuster>() {  };

  private final Fixture fixture = new Fixture();

  @Theory
  public void shouldGenerateDifferentValuesEachTime(InstanceOf<?> instanceOfType) {
    System.out.println(instanceOfType.toString());
    Object o1 = fixture.create(instanceOfType);
    Object o2 = fixture.create(instanceOfType);

    assertThat(o1, instanceOf(instanceOfType.getRawType()));
    assertThat(o1, not(nullValue()));
    assertThat(o2, not(nullValue()));
    assertThat(o1, not(o2));
    assertThat(o1, not(equalTo(o2)));
  }

}
