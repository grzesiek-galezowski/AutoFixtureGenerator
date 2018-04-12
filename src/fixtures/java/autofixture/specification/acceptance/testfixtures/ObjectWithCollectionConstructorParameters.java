package autofixture.specification.acceptance.testfixtures;

import java.util.AbstractList;
import java.util.Set;

public class ObjectWithCollectionConstructorParameters {

  public AbstractList<Integer> intListParameter;
  public String[][] stringArrayParameter;
  public Set<Set<Double>> doubleSetSetParameter;

  public ObjectWithCollectionConstructorParameters(
    AbstractList<Integer> parameter1,
    String[][] parameter2,
    Set<Set<Double>> parameter3) {
    this.intListParameter = parameter1;
    this.stringArrayParameter = parameter2;
    this.doubleSetSetParameter = parameter3;
  }
}
