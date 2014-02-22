package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class CalendarGenerator implements InstanceGenerator {
	int secondsToAdd = 0;
	
	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.getRawType() == Calendar.class;
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, secondsToAdd++);
		return (T)calendar;
	}
}
