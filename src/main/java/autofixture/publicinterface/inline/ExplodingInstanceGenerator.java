package autofixture.publicinterface.inline;

import autofixture.exceptions.OnlyInterfacesAreSupportedException;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineInstanceGenerator;
import autofixture.publicinterface.inline.implementationdetails.ExplodingInstanceHandler;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;

public class ExplodingInstanceGenerator<T> implements InlineInstanceGenerator<T> {

  private final TypeToken<T> instance;

  public ExplodingInstanceGenerator(final TypeToken<T> instance2) {
    this.instance = instance2;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T next(final FixtureContract fixture) {
    if (instance.getRawType().isInterface()) {
      return (T) Reflection.newProxy(instance.getRawType(),
          new ExplodingInstanceHandler());
    } else {
      throw new OnlyInterfacesAreSupportedException(
          "Exploding instances can be created out of interfaces only!");
    }

  }

}
