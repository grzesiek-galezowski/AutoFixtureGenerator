package autofixture.publicinterface.constraints;

/**
 * Created by astral on 27.03.15.
 */
public interface GenerationConstraint<T> {
  boolean doesNotAccept(T currentValue);
}
