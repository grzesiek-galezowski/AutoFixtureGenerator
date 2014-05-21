package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;

import java.util.Calendar;
import java.util.Date;

public class DateGenerator implements InstanceGenerator {
	
	Calendar calendar = Calendar.getInstance();
	
	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(Date.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		calendar.add(Calendar.SECOND, 1);
		return (T)calendar.getTime();
	}

	@Override
	public void setOmittingAutoProperties(boolean isOn) {
	}
}
