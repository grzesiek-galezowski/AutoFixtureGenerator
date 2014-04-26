package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

public class EnumGenerator implements InstanceGenerator {

	private EnumCache enumCache;

	public EnumGenerator(EnumCache enumCache) {
		this.enumCache = enumCache;
	}

	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isEnum();
	}

	@Override
	public <T> T next(InstanceType<T> type, FixtureContract fixture) {
		
		enumCache.registerIfNotPresent(type);
		return enumCache.retrieveNextValueOf(type);
		
		/*
		Object[] enumElements = type.getEnumConstants();
		int idx = new Random().nextInt(enumElements.length);
		return (T)(enumElements[idx]);
		//TODO make it sequential! Random is bad...*/
	}

}
