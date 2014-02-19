package jfixture.publicinterface.generators;

public class PlainObjectGenerator implements ObjectGenerator {
	public Object next() {
		return new Object();
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == Object.class;
	}
}
