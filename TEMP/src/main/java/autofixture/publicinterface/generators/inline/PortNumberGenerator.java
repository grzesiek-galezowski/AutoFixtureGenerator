package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

import java.util.Random;

public class PortNumberGenerator implements InlineInstanceGenerator<Integer> {
  public static final int MAXIMUM_PORT_NUMBER = 65535;
  private final Random random = new Random();

  @Override
  public Integer next(final FixtureContract fixture) {
    return random.nextInt(MAXIMUM_PORT_NUMBER);
  }

}
