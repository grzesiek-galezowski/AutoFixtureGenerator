package autofixture.publicinterface.generators.implementationdetails;

import autofixture.publicinterface.InstanceType;

import java.util.ArrayList;
import java.util.Random;

public class CircularList<T> {

  private static final Random RANDOM = new Random();
  private final T[] enumConstants;
  private int currentIndex = 0;

  public CircularList(final T[] values) {
    this.enumConstants = values.clone();
    currentIndex = RANDOM.nextInt(values.length);
  }

  public static <TListElement> CircularList<TListElement> createFromEnum(final InstanceType<TListElement> type) {
    final TListElement[] enumConstants = type.getEnumConstants();

    return new CircularList<>(enumConstants);
  }

  public static CircularList<Character> fromCharactersIn(final String string) {
    final ArrayList<Character> chars = new ArrayList<>();
    string.chars().forEachOrdered(i -> chars.add((char) i));
    return new CircularList<>(chars.toArray(new Character[chars.size()]));
  }

  public T next() {
    if (currentIndex >= enumConstants.length) {
      currentIndex = 0;
    }

    final T nextElement = enumConstants[currentIndex];
    currentIndex++;
    return nextElement;
  }


}
