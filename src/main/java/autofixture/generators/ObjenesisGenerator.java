package autofixture.generators;

/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceType;
import org.objenesis.ObjenesisStd;

public class ObjenesisGenerator implements InstanceGenerator {

    private final ObjenesisStd objenesis = new ObjenesisStd(true);

    @Override
    public <T> boolean appliesTo(InstanceType<T> instanceType) {
        return true;
    }

    @Override
    public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
        return objenesis.newInstance((Class<T>)instanceType.getRawType());
    }

    @Override
    public void setOmittingAutoProperties(boolean isOn) {

    }
}