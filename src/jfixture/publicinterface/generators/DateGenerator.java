package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class DateGenerator implements InstanceGenerator {
	
	Calendar calendar = Calendar.getInstance();
	
	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(Date.class);
	}

	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		calendar.add(Calendar.SECOND, 1);
		return (T)calendar.getTime();
	}
}
