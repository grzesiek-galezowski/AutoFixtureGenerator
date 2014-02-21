package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public interface PrimitiveGenerator {
	boolean AppliesTo(TypeToken<?> typeToken);
	Object next();
}
