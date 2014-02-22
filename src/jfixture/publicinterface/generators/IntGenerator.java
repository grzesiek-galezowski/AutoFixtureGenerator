package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class IntGenerator implements InstanceGenerator {

	public Integer startingInteger = 1;

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		// TODO Auto-generated method stub
		return typeToken.getRawType() == int.class
				|| typeToken.getRawType() == Integer.class
				|| typeToken.getRawType() == short.class
				|| typeToken.getRawType() == Short.class
				|| typeToken.getRawType() == long.class
				|| typeToken.getRawType() == Long.class;
	}
	
	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		return (T)(startingInteger++);
	}

}
