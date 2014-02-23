package jfixture.publicinterface.generators;

import java.util.Arrays;
import java.util.LinkedList;

import jfixture.publicinterface.generators.ArrayGenerator;
import jfixture.publicinterface.generators.BigDecimalGenerator;
import jfixture.publicinterface.generators.BigIntGenerator;
import jfixture.publicinterface.generators.BooleanGenerator;
import jfixture.publicinterface.generators.BuiltInCollectionGenerator;
import jfixture.publicinterface.generators.ByteAndCharGenerator;
import jfixture.publicinterface.generators.CalendarGenerator;
import jfixture.publicinterface.generators.ConcreteObjectGenerator;
import jfixture.publicinterface.generators.DateGenerator;
import jfixture.publicinterface.generators.DoubleGenerator;
import jfixture.publicinterface.generators.EnumGenerator;
import jfixture.publicinterface.generators.InstanceGenerator;
import jfixture.publicinterface.generators.IntGenerator;
import jfixture.publicinterface.generators.PlainObjectGenerator;
import jfixture.publicinterface.generators.StringGenerator;

public class GeneratorsFactory {
	public LinkedList<InstanceGenerator> createBuiltinGenerators() {
		return new LinkedList<InstanceGenerator>(
				Arrays.asList(new InstanceGenerator[] {
				new EnumGenerator(),
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
				new ArrayGenerator(),
				new BuiltInCollectionGenerator(),
				new ConcreteObjectGenerator()
		}));
	}
}
