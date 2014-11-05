package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.generators.implementationdetails.CircularList;

import java.awt.color.ColorSpace;

/**
 * Created by astral on 11.10.14.
 */
public class ColorSpaceGenerator implements InstanceGenerator {

    CircularList<Integer> codes = new CircularList<>(new Integer[] {
            ColorSpace.CS_CIEXYZ,
            ColorSpace.CS_GRAY,
            ColorSpace.CS_LINEAR_RGB,
            ColorSpace.CS_PYCC,
            ColorSpace.CS_sRGB
    });

    @Override
    public <T> boolean appliesTo(InstanceType<T> instanceType) {
        return instanceType.isRawTypeAssignableFrom(ColorSpace.class);
    }

    @Override
    public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
        int anyInt = fixture.create(int.class);
        return (T)ColorSpace.getInstance(codes.next());
    }

    @Override
    public void setOmittingAutoProperties(boolean isOn) {

    }
}
