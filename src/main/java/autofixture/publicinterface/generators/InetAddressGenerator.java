package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.ObjectCreationException;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class InetAddressGenerator implements InstanceGenerator {
	Random random = new Random();
	
	@Override
	public <T> boolean appliesTo(InstanceType<T> instanceType) {
		return instanceType.isRawTypeAssignableFrom(InetAddress.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
		byte[] bytes = new byte[4];
		random.nextBytes(bytes);
		InetAddress address;
		try {
			address = Inet4Address.getByAddress(bytes);
		} catch (UnknownHostException e) {
			throw new ObjectCreationException(instanceType, e);
		}
		return (T) address;
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
	}

}
