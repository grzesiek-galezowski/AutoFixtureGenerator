package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarGenerator implements ObjectGenerator {
	
	public Object next() {
		return new GregorianCalendar();
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == Calendar.class;
	}
}
