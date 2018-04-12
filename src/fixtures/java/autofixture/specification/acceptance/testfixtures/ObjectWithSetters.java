package autofixture.specification.acceptance.testfixtures;

public class ObjectWithSetters {
  private int age;
  private String name;

  public int getAge() {
    return this.age;
  }

  public void setAge(int newAge) {
    this.age = newAge;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setReplication(String replicationData) {
    throw new RuntimeException();
  }
}
