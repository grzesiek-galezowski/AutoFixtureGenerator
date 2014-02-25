package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class IntGenerator implements InstanceGenerator {

	public Integer startingInteger = 1;

	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		// TODO Auto-generated method stub
		return typeToken.isRawTypeAssignableFrom(int.class)
				|| typeToken.isRawTypeAssignableFrom(Integer.class)
				|| typeToken.isRawTypeAssignableFrom(short.class)
				|| typeToken.isRawTypeAssignableFrom(Short.class)
				|| typeToken.isRawTypeAssignableFrom(long.class)
				|| typeToken.isRawTypeAssignableFrom(Long.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		return (T)(startingInteger++);
	}

}
