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

  private final CircularList<Integer> codes = new CircularList<>(new Integer[]{
      ColorSpace.CS_CIEXYZ,
      ColorSpace.CS_GRAY,
      ColorSpace.CS_LINEAR_RGB,
      ColorSpace.CS_PYCC,
      ColorSpace.CS_sRGB
  });

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ColorSpace.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) ColorSpace.getInstance(codes.next());
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
