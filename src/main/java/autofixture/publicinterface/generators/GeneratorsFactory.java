package autofixture.publicinterface.generators;

import java.util.Arrays;
import java.util.LinkedList;

import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.generators.ArrayGenerator;
import autofixture.publicinterface.generators.BigDecimalGenerator;
import autofixture.publicinterface.generators.BigIntGenerator;
import autofixture.publicinterface.generators.BooleanGenerator;
import autofixture.publicinterface.generators.BuiltInCollectionGenerator;
import autofixture.publicinterface.generators.ByteAndCharGenerator;
import autofixture.publicinterface.generators.CalendarGenerator;
import autofixture.publicinterface.generators.ConcreteObjectGenerator;
import autofixture.publicinterface.generators.DateGenerator;
import autofixture.publicinterface.generators.DoubleGenerator;
import autofixture.publicinterface.generators.EnumGenerator;
import autofixture.publicinterface.generators.IntGenerator;
import autofixture.publicinterface.generators.PlainObjectGenerator;
import autofixture.publicinterface.generators.StringGenerator;
import autofixture.publicinterface.generators.implementationdetails.InMemoryEnumCache;

public class GeneratorsFactory {
	public GeneratorsPipeline createBuiltinGenerators() {
		return new GeneratorsPipeline(
				new LinkedList<InstanceGenerator>(Arrays.asList(new InstanceGenerator[] {
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
						new ConcreteObjectGenerator()
						})));
	}
}
