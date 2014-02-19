package jfixture.publicinterface.generators;

public class DoubleGenerator implements ObjectGenerator {
	private Double startingNumber = 0.3;
	public Object next() {
		return startingNumber++;
	}
	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == double.class
				|| clazz == Double.class
				|| clazz == float.class
			    || clazz == Float.class; 
	}
}
