package autofixture.implementationdetails;

import autofixture.exceptions.ObjectCreationException;
import autofixture.interfaces.Call;
import autofixture.interfaces.FixtureContract;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Parameter;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class MethodCall<TOwnerType, TReturnType> implements Call<TOwnerType, TReturnType> {

  private final Invokable<TOwnerType, TReturnType> invokable;

  public MethodCall(final Invokable<TOwnerType, TReturnType> invokable) {
    this.invokable = invokable;
  }

  public static <TElement1, TElement2>
  Call<TElement1, TElement2> to(final Invokable<TElement1, TElement2> invokable) {
    return new MethodCall<>(invokable);
  }

  @Override
  public ImmutableList<Parameter> getParameters() {
    return invokable.getParameters();
  }

  private TReturnType invoke(final TOwnerType ownerType, final List<Object> arguments) {
    try {
      return invokable.invoke(ownerType, arguments.toArray());
    } catch (InvocationTargetException | IllegalAccessException e) {
      throw new ObjectCreationException(
          new ConcreteInstanceType<>(invokable.getOwnerType()), e);
    } catch (final IllegalArgumentException e) {
      throw new ObjectCreationException(
          new ConcreteInstanceType<>(invokable.getOwnerType()),
          "Inner classes are not supported for now. \nCaused by " + e.toString(), e);

    }
  }

  @Override
  public TReturnType invokeWithArgumentsCreatedUsing(
    final FixtureContract fixture, final Optional<TOwnerType> optionalOwnerType) {
    final List<Object> arguments = prepareArgumentsOf(this, fixture);
    final TReturnType instance = invoke(optionalOwnerType.orNull(), arguments);
    return instance;
  }

  private List<Object> prepareArgumentsOf(
      final Call<TOwnerType, TReturnType> invokable, final FixtureContract fixture) {
    final ArrayList<Object> arguments = new ArrayList<>();

    for (final Parameter parameter : invokable.getParameters()) {
      addInstanceOf(parameter, arguments, fixture);
    }

    return arguments;
  }

  private void addInstanceOf(final Parameter parameter,
                             final List<Object> arguments, final FixtureContract fixture) {
    if (isParameterized(parameter)) {
      arguments.add(fixture.create(realTypeOf(parameter)));
    } else {
      arguments.add(fixture.create(parameter.getType()));
    }
  }

  private TypeToken<?> realTypeOf(final Parameter parameter) {
    return TypeToken.of(parameter.getType().getType());
  }

  private boolean isParameterized(final Parameter parameter) {
    return parameter.getType().getType() instanceof ParameterizedType;
  }

  @Override
  public TReturnType invokeWithArgumentsCreatedUsing(final FixtureContract fixture) {
    return invokeWithArgumentsCreatedUsing(fixture, Optional.absent());
  }


}
