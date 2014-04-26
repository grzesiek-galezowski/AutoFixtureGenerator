package autofixture.publicinterface.generators.implementationdetails;

import java.util.HashMap;

import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.generators.EnumCache;

public class InMemoryEnumCache implements EnumCache {

	private final HashMap<InstanceType<?>, CircularList<?>> sequences = new HashMap<>();

	@Override
	public <T> void registerIfNotPresent(InstanceType<T> instanceType) {
		if(!sequences.containsKey(instanceType)) {
			sequences.put(instanceType, CircularList.createFromEnum(instanceType));
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T retrieveNextValueOf(InstanceType<T> instanceType) {
		return (T) sequences.get(instanceType).next();
	}
}
