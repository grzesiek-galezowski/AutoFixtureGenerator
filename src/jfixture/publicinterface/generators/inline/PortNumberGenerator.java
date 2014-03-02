package jfixture.publicinterface.generators.inline;

import java.util.Random;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;

public class PortNumberGenerator implements InlineInstanceGenerator<Integer> {
	private Random random = new Random();
	
	@Override
	public Integer next(FixtureContract fixture) {
		return random.nextInt(65535);
	}

}
