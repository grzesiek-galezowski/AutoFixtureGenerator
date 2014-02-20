package jfixture.publicinterface.generators;

public class BooleanGenerator implements ObjectGenerator {
	private Boolean currentValue = false;
	
	public Object next() {
		currentValue = !currentValue;
		return currentValue;
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == boolean.class
				|| clazz == Boolean.class;
	}
}
