package autofixture.implementationdetails;

import java.util.Map;
import java.util.Random;

public class InstanceCursor {
  private static final Random RANDOM = new Random();
  private Integer value;
  private final Class key;
  private final Map<Class, InstanceCursor> indicesByClass;

  public InstanceCursor(
      final Integer value,
      final Class key,
      final Map<Class, InstanceCursor> indicesByClass) {
    this.value = value;
    this.key = key;
    this.indicesByClass = indicesByClass;
  }

  public static InstanceCursor randomUpTo(final int length, final Class key, final Map<Class, InstanceCursor> indicesByClass) {
    return new InstanceCursor(RANDOM.nextInt(length), key, indicesByClass);
  }

  public static InstanceCursor from(final Map<Class, InstanceCursor> indicesByClass, final Class key, final int length) {
    if (!indicesByClass.containsKey(key)) {
      InstanceCursor index = randomUpTo(length, key, indicesByClass);
      index.save();
      return index;
    } else {
      InstanceCursor index = indicesByClass.get(key);
      index.resetIfExceeds(length);
      return index;
    }
  }

  public <T> T selectFrom(
      final T[] possibleValues) {
    return possibleValues[this.value];
  }

  public Integer advance() {
    return this.value++;
  }

  public void save() {
    this.indicesByClass.put(this.key, this);
  }

  public void resetIfExceeds(final int length) {
    if (this.value >= length) {
      this.value = 0;
    }
  }
}
