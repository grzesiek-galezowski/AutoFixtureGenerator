package jfixture.publicinterface.generators;

import java.math.BigDecimal;

import com.google.common.reflect.TypeToken;

public class BigDecimalGenerator implements ObjectGenerator {
	private IntGenerator intGenerator = new IntGenerator();
	
	public Object next() {
		return new BigDecimal((int)intGenerator.next());
	}

	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.isAssignableFrom(BigDecimal.class);
	}
}
