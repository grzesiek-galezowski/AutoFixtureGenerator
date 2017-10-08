package autofixture.implementationdetails;

import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Boxing {
  public static Long[] boxed(final long[] other) {
    return LongStream.of(other).boxed().toArray(Long[]::new);
  }

  public static Float[] boxed(final float[] other) {
    final ArrayList<Float> list = new ArrayList<>();
    for (final float f : other) {
      list.add(f);
    }
    return list.toArray(new Float[]{});
  }

  public static Short[] boxed(final short[] other) {
    final ArrayList<Short> list = new ArrayList<>();
    for (final short s : other) {
      list.add(s);
    }
    return list.toArray(new Short[]{});
  }

  public static Double[] boxed(final double[] other) {
    return DoubleStream.of(other).boxed().toArray(Double[]::new);
  }

  public static Integer[] boxed(final int... other) {
    return IntStream.of(other).boxed().toArray(Integer[]::new);
  }
}
