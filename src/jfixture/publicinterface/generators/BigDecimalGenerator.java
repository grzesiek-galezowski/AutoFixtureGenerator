package jfixture.publicinterface.generators;

import java.math.BigDecimal;

public class BigDecimalGenerator implements ObjectGenerator {
	private IntGenerator intGenerator = new IntGenerator();
	
	public Object next() {
		return new BigDecimal((int)intGenerator.next());
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == BigDecimal.class;
	}
}
