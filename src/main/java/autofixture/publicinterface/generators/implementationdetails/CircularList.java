package autofixture.publicinterface.generators.implementationdetails;

import autofixture.publicinterface.InstanceType;

import java.util.ArrayList;
import java.util.Random;

public class CircularList<T> {

  private static Random random = new Random();
  private T[] enumConstants;
  private int currentIndex = 0;

  public CircularList(T[] values) {
    this.enumConstants = values;
    currentIndex = random.nextInt(values.length);
  }

  public static <TListElement> CircularList<TListElement> createFromEnum(InstanceType<TListElement> type) {
    TListElement[] enumConstants = type.getEnumConstants();

    return new CircularList<>(enumConstants);
  }

  public static CircularList<Character> fromCharactersIn(String string) {
    ArrayList<Character> chars = new ArrayList<>();
    string.chars().forEachOrdered(i -> chars.add((char) i));
    return new CircularList<>(chars.toArray(new Character[chars.size()]));
  }

  public T next() {
    if (currentIndex >= enumConstants.length) {
      currentIndex = 0;
    }

    T nextElement = enumConstants[currentIndex];
    currentIndex++;
    return nextElement;
  }


}
