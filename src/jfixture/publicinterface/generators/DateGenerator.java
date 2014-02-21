package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;

import com.google.common.reflect.TypeToken;

public class DateGenerator implements PrimitiveGenerator {
	
	Calendar calendar = Calendar.getInstance();
	
	public Object next() {
		calendar.add(Calendar.SECOND, 1);
		return calendar.getTime();
	}

	@Override
	public boolean AppliesTo(TypeToken<?> clazz) {
		return clazz.getRawType() == Date.class;
	}
}
