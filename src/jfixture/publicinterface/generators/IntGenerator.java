package jfixture.publicinterface.generators;

public class IntGenerator implements ObjectGenerator {

	public int startingInteger = 1;
	public Object next() {
		return startingInteger++;
	}
	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == int.class
				|| clazz == Integer.class
				|| clazz == short.class
				|| clazz == Short.class
				|| clazz == long.class
				|| clazz == Long.class;
	}

}
