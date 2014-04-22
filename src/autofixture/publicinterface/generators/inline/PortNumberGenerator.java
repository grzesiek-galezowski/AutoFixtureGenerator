package autofixture.publicinterface.generators.inline;

import java.util.Random;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

public class PortNumberGenerator implements InlineInstanceGenerator<Integer> {
	private Random random = new Random();
	
	@Override
	public Integer next(FixtureContract fixture) {
		return random.nextInt(65535);
	}

}
