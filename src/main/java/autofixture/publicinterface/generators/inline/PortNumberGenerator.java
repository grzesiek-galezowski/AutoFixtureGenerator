package autofixture.publicinterface.generators.inline;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InlineInstanceGenerator;

import java.util.Random;

public class PortNumberGenerator implements InlineInstanceGenerator<Integer> {
  private final Random random = new Random();

  @Override
  public Integer next(final FixtureContract fixture) {
    return random.nextInt(65535);
  }

}
