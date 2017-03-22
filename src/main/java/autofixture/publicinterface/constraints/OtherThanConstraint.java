package autofixture.publicinterface.constraints;

import autofixture.interfaces.GenerationConstraint;

import java.util.Arrays;

/**
 * Created by astral on 27.03.15.
 */
public class OtherThanConstraint<T> implements GenerationConstraint<T> {
  private T[] array;

  public OtherThanConstraint(T... values) {
    this.array = values;
  }

  @Override
  public boolean doesNotAccept(T currentValue) {
    return Arrays.asList(array).contains(currentValue);
  }

}
