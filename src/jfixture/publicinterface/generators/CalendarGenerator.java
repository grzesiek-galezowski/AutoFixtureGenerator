package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.GregorianCalendar;

import jfixture.publicinterface.FixtureContract;
import jfixture.publicinterface.InstanceGenerator;
import jfixture.publicinterface.InstanceType;

public class CalendarGenerator implements InstanceGenerator {
	int secondsToAdd = 0;
	
	@Override
	public <T> boolean appliesTo(InstanceType<T> typeToken) {
		return typeToken.isRawTypeAssignableFrom(Calendar.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T next(InstanceType<T> typeToken, FixtureContract fixture) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, secondsToAdd++);
		return (T)calendar;
	}
}
