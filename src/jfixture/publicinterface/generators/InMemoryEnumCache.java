package jfixture.publicinterface.generators;

import java.util.ArrayList;
import java.util.HashMap;

import jfixture.publicinterface.InstanceType;

public class InMemoryEnumCache implements EnumCache {

	private final HashMap<InstanceType<?>, CircularList<?>> sequences = new HashMap<>();

	@Override
	public <T> void registerIfNotPresent(InstanceType<T> instanceType) {
		if(!sequences.containsKey(instanceType)) {
			sequences.put(instanceType, CircularList.createFromEnum(instanceType));
		}		
	}

	@Override
	public <T> T retrieveNextValueOf(InstanceType<T> instanceType) {
		return (T) sequences.get(instanceType).next();
	}


}
