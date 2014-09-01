package autofixture.publicinterface.generators;

import autofixture.publicinterface.generators.implementationdetails.InMemoryEnumCache;

import java.util.Arrays;
import java.util.LinkedList;

public class GeneratorsFactory {
	public GeneratorsPipeline createBuiltinGenerators(RecursionGuard recursionGuard) {
		return recursionGuarded(
                new DefaultGeneratorsPipeline(
                        new LinkedList<>(Arrays.asList(
                                enums(),
                                exceptions(),
                                errors(),
                                strings(),
                                integers(),
                                doubles(),
                                bigIntegers(),
                                bigDecimals(),
                                dates(),
                                calendars(),
                                objects(),
                                bytesAndChars(),
                                booleans(),
                                urls(),
                                arrays(),
                                builtInCollections(),
                                inetAddresses(),
                                interfaceImplementations(),
                                concreteObjects()))),
                recursionGuard);
	}

    private GeneratorsPipeline recursionGuarded(
            DefaultGeneratorsPipeline defaultGeneratorsPipeline, RecursionGuard recursionGuard) {
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

    private IntSequenceGenerator integers() {
        return new IntSequenceGenerator();
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
