package jfixture.publicinterface.generators.inline;

import com.google.common.reflect.Reflection;

import jfixture.publicinterface.ExplodingInstanceHandler;
import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InlineInstanceGenerator;
import jfixture.publicinterface.InstanceOf;
import jfixture.publicinterface.InterfacesNotSupportedException;

public class ExplodingInstanceGenerator<T> implements InlineInstanceGenerator<T> {

	private final InstanceOf<T> instance;

	public ExplodingInstanceGenerator(InstanceOf<T> instance) {
		this.instance = instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next(FixtureContract fixture) {
		if (instance.isInterface()) {
			throw new InterfacesNotSupportedException(
					"Exploding instances can be created out of interfaces only!");
		} else {
			return (T) Reflection.newProxy(instance.getRawType(),
					new ExplodingInstanceHandler());
		}

	}

}
