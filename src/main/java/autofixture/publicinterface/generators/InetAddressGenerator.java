package autofixture.publicinterface.generators;

import autofixture.exceptions.ObjectCreationException;
import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class InetAddressGenerator implements InstanceGenerator {
  public static final int NUMBER_OF_BYTES_IN_IP_ADDRESS = 4;
  private final Random random = new Random();

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(InetAddress.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    final byte[] bytes = new byte[NUMBER_OF_BYTES_IN_IP_ADDRESS];
    random.nextBytes(bytes);
    final InetAddress address;
    try {
      address = Inet4Address.getByAddress(bytes);
    } catch (final UnknownHostException e) {
      throw new ObjectCreationException(instanceType, e);
    }
    return (T) address;
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {
  }

}
