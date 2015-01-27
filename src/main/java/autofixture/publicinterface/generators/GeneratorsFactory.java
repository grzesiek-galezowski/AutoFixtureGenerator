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
