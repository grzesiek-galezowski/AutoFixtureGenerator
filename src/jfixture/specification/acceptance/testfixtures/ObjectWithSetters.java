package jfixture.specification.acceptance.testfixtures;

public class ObjectWithSetters {
	private int age;
	private String name;
	
	public void setAge(int newAge) {
		this.age = newAge;
	}
	
	public int getAge() {
		return this.age;
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
