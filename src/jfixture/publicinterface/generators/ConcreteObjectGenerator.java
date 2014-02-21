package jfixture.publicinterface.generators;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class ConcreteObjectGenerator implements CompoundObjectGenerator {

	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture)
			throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
