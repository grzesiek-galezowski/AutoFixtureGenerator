package autofixture.publicinterface.generators;

import autofixture.publicinterface.generators.implementationdetails.InMemoryEnumCache;

import java.util.Arrays;
import java.util.LinkedList;

public class GeneratorsFactory {
	public GeneratorsPipeline createBuiltinGenerators(RecursionGuard recursionGuard) {
		return new RecursionGuarded(
                new DefaultGeneratorsPipeline(
				    new LinkedList<>(Arrays.asList(
                        new EnumGenerator(new InMemoryEnumCache()),
                        new ExceptionGenerator(),
                        new ErrorGenerator(),
                        new StringGenerator(),
                        new IntGenerator(),
                        new DoubleGenerator(),
                        new BigIntGenerator(),
                        new BigDecimalGenerator(),
                        new DateGenerator(),
                        new CalendarGenerator(),
                        new PlainObjectGenerator(),
                        new ByteAndCharGenerator(),
                        new BooleanGenerator(),
                        new UrlGenerator(),
                        new ArrayGenerator(),
                        new BuiltInCollectionGenerator(),
                        new InetAddressGenerator(),
                        new InterfaceImplementationGenerator(),
                        new ConcreteObjectGenerator()))),
                recursionGuard);
	}
}
