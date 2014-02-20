package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;

public class DateGenerator implements ObjectGenerator {
	
	Calendar calendar = Calendar.getInstance();
	
	public Object next() {
		calendar.add(Calendar.SECOND, 1);
		return calendar.getTime();
	}

	@Override
	public boolean AppliesTo(Class<?> clazz) {
		return clazz == Date.class;
	}
}
