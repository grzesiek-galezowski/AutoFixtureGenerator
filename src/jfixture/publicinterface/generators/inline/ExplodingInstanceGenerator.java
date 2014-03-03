package jfixture.publicinterface.generators.inline;

import jfixture.implementationdetails.ExplodingInstanceHandler;
import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;
import jfixture.publicinterface.InterfacesNotSupportedException;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

public class ExplodingInstanceGenerator<T> implements InlineInstanceGenerator<T> {

	private final TypeToken<T> instance;

	public ExplodingInstanceGenerator(TypeToken<T> instance2) {
		this.instance = instance2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next(FixtureContract fixture) {
		if (instance.getRawType().isInterface()) {
			throw new InterfacesNotSupportedException(
					"Exploding instances can be created out of interfaces only!");
		} else {
			return (T) Reflection.newProxy(instance.getRawType(),
					new ExplodingInstanceHandler());
		}

	}

}
