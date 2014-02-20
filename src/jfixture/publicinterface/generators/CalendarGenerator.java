package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarGenerator implements ObjectGenerator {
	int secondsToAdd = 0;
	
	public Object next() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, secondsToAdd++);
		return calendar;
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == Calendar.class;
	}
}
