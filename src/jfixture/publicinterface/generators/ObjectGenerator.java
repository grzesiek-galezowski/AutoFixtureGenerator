package jfixture.publicinterface.generators;

import com.google.common.reflect.TypeToken;

public interface ObjectGenerator {
	boolean AppliesTo(TypeToken<?> typeToken);
	Object next();
}
