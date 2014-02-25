package jfixture.publicinterface.generators;

import jfixture.publicinterface.InstanceType;
import jfixture.specification.acceptance.EnumTypesGenerationSpecification.Eon;

public interface EnumCache {

	<T> void registerIfNotPresent(InstanceType<T> instanceType);

	<T> T retrieveNextValueOf(InstanceType<T> instanceType);

}
