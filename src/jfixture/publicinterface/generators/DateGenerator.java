package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;

public class DateGenerator implements ObjectGenerator {
	
	public Object next() {
		return Calendar.getInstance().getTime();
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == Date.class;
	}
}
