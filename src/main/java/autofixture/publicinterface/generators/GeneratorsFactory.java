package autofixture.publicinterface.generators;

import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.generators.implementationdetails.InMemoryEnumCache;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GeneratorsFactory {
  public GeneratorsPipeline createBuiltinGenerators(RecursionGuard recursionGuard) {
    return
      protectedBy(recursionGuard,
        pipelineOfGeneratorsForTypes(
          matchedInTheFollowingOrder(
            integers(),
            enums(),
            exceptions(),
            errors(),
            strings(),
            doubles(),
            floats(),
            bigIntegers(),
            bigDecimals(),
            dates(),
            calendars(),
            chronoLocalDates(),
            chronoLocalDateTimes(),
            localDateTimes(),
            localDates(),
            zonedDateTimes(),
            zoneIds(),
            offsetTimes(),
            periods(),
            durations(),
            zoneOffsets(),
            clocks(),
            instants(),
            objects(),
            booleans(),
            urls(),
            arrays(),
            optionals(),
            builtInCollections(),
            inetAddresses(),
            interfaceImplementations(),
            colorSpaces(),
            concreteObjects()))
      );
  }

  private InstanceGenerator instants() {
    return new InstantGenerator();
  }

  private InstanceGenerator clocks() {
    return ClockGenerator();
  }

  private InstanceGenerator ClockGenerator() {
    return new ClockGenerator();
  }

  private InstanceGenerator zoneOffsets() {
    return new ZoneOffsetGenerator();
  }

  private InstanceGenerator chronoLocalDateTimes() {
    return new ChronoLocalDateTimeGenerator();
  }

  private InstanceGenerator chronoLocalDates() {
    return new ChronoLocalDateGenerator();
  }

  private InstanceGenerator durations() {
    return new DurationGenerator();
  }

  private InstanceGenerator periods() {
    return new PeriodGenerator();
  }

  private InstanceGenerator offsetTimes() {
    return new OffsetTimeGenerator();
  }

  private InstanceGenerator zoneIds() {
    return new ZoneIdGenerator();
  }

  private InstanceGenerator zonedDateTimes() {
    return new ZonedDateTimeGenerator();
  }

  private InstanceGenerator localDates() {
    return new LocalDateGenerator();
  }

  private InstanceGenerator localDateTimes() {
    return new LocalDateTimeGenerator();
  }

  /*
  @DataPoint public static InstanceOf<LocalDateTime> localDateTime = new InstanceOf<LocalDateTime>() {  };
  @DataPoint public static InstanceOf<LocalDate> localDate = new InstanceOf<LocalDate>() {  };
  @DataPoint public static InstanceOf<Month> month = new InstanceOf<Month>() {  };
  @DataPoint public static InstanceOf<ZonedDateTime> zonedDateTime = new InstanceOf<ZonedDateTime>() {  };
  @DataPoint public static InstanceOf<ZoneId> zoneId = new InstanceOf<ZoneId>() {  };
  @DataPoint public static InstanceOf<OffsetTime> offsetTime = new InstanceOf<OffsetTime>() {  };
  @DataPoint public static InstanceOf<Period> period = new InstanceOf<Period>() {  };
  @DataPoint public static InstanceOf<Duration> duration = new InstanceOf<Duration>() {  };
  @DataPoint public static InstanceOf<Chronology> chronology = new InstanceOf<Chronology>() {  };
  @DataPoint public static InstanceOf<ChronoLocalDate> chronoLocalDate = new InstanceOf<ChronoLocalDate>() {  };
  @DataPoint public static InstanceOf<ChronoLocalDateTime> chronoLocalDateTime = new InstanceOf<ChronoLocalDateTime>() {  };
  @DataPoint public static InstanceOf<ChronoZonedDateTime> chronoZonedDateTime = new InstanceOf<ChronoZonedDateTime>() {  };
  @DataPoint public static InstanceOf<ZoneOffset> zoneOffset = new InstanceOf<ZoneOffset>() {  };

  */


  private InstanceGenerator colorSpaces() {
    return new ColorSpaceGenerator();
  }

  private InstanceGenerator optionals() {
    return new OptionalsGenerator();
  }

  private DefaultGeneratorsPipeline pipelineOfGeneratorsForTypes(List<InstanceGenerator> generators) {
    return new DefaultGeneratorsPipeline(generators);
  }

  public java.util.List<InstanceGenerator> matchedInTheFollowingOrder(InstanceGenerator... ts) {
    return new LinkedList<>(Arrays.asList(ts));
  }

  private RandomNumberGenerator integers() {
    return new RandomNumberGenerator();
  }

  private GeneratorsPipeline protectedBy(
    RecursionGuard recursionGuard, DefaultGeneratorsPipeline defaultGeneratorsPipeline) {
    return new RecursionGuarded(defaultGeneratorsPipeline, recursionGuard);
  }

  private ConcreteObjectGenerator concreteObjects() {
    return new ConcreteObjectGenerator();
  }

  private InterfaceImplementationGenerator interfaceImplementations() {
    return new InterfaceImplementationGenerator();
  }

  private InetAddressGenerator inetAddresses() {
    return new InetAddressGenerator();
  }

  private BuiltInCollectionGenerator builtInCollections() {
    return new BuiltInCollectionGenerator();
  }

  private ArrayGenerator arrays() {
    return new ArrayGenerator();
  }

  private UrlGenerator urls() {
    return new UrlGenerator();
  }

  private BooleanGenerator booleans() {
    return new BooleanGenerator();
  }

  private ByteAndCharSequenceGenerator bytesAndChars() {
    return new ByteAndCharSequenceGenerator();
  }

  private PlainObjectGenerator objects() {
    return new PlainObjectGenerator();
  }

  private CalendarGenerator calendars() {
    return new CalendarGenerator();
  }

  private DateGenerator dates() {
    return new DateGenerator();
  }

  private BigDecimalGenerator bigDecimals() {
    return new BigDecimalGenerator();
  }

  private BigIntGenerator bigIntegers() {
    return new BigIntGenerator();
  }

  private DoubleSequenceGenerator doubles() {
    return new DoubleSequenceGenerator();
  }

  private FloatSequenceGenerator floats() {
    return new FloatSequenceGenerator();
  }

  private StringGenerator strings() {
    return new StringGenerator();
  }

  private ErrorGenerator errors() {
    return new ErrorGenerator();
  }

  private ExceptionGenerator exceptions() {
    return new ExceptionGenerator();
  }

  private EnumSequenceGenerator enums() {
    return new EnumSequenceGenerator(new InMemoryEnumCache());
  }
}
