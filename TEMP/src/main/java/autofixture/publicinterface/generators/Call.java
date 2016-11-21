package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Parameter;

public interface Call<TOwnerType, TReturnType> {

  ImmutableList<Parameter> getParameters();

  TReturnType invokeWithArgumentsCreatedUsing(
      FixtureContract fixture, Optional<TOwnerType> optionalOwnerType);

  TReturnType invokeWithArgumentsCreatedUsing(
      FixtureContract fixture);


}
