package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.GregorianCalendar;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceType;

public class CalendarGenerator implements InstanceGenerator {
	int secondsToAdd = 0;
	
	@Override
	public <T> boolean AppliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(Calendar.class);
	}

	@Override
	public <T> T next(InstanceType<T> typeToken, Fixture fixture) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, secondsToAdd++);
		return (T)calendar;
	}
}
