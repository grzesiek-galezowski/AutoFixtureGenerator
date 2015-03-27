package autofixture.publicinterface.constraints;

/**
 * Created by astral on 27.03.15.
 */
public class GenerationConstraints {
  public static <T> OtherThanConstraint<T> otherThan(T... values) {
    return new OtherThanConstraint(values);
  }

  public static <T> OtherThanConstraint<T> without(T... values) {
    return new OtherThanConstraint(values);
  }
}
