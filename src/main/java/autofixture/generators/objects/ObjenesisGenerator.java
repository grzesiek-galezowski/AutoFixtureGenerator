package autofixture.generators.objects;

/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import org.objenesis.ObjenesisStd;

public class ObjenesisGenerator implements InstanceGenerator {

    private final ObjenesisStd objenesis = new ObjenesisStd(true);

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return objenesis.newInstance((Class<T>)instanceType.getRawType());
  }


  @Override
    public <T> boolean appliesTo(InstanceType<T> instanceType) {
        return true;
    }


  @Override
  public <T> T nextEmpty(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return next(instanceType, fixture);
  }

  @Override
    public void setOmittingAutoProperties(boolean isOn) {

    }
}