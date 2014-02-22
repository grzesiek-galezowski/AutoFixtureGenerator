package jfixture.publicinterface.generators;

import java.util.Calendar;
import java.util.Date;

import jfixture.publicinterface.Fixture;

import com.google.common.reflect.TypeToken;

public class DateGenerator implements InstanceGenerator {
	
	Calendar calendar = Calendar.getInstance();
	
	@Override
	public <T> boolean AppliesTo(TypeToken<T> typeToken) {
		return typeToken.getRawType() == Date.class;
	}

	@Override
	public <T> T next(TypeToken<T> typeToken, Fixture fixture) {
		calendar.add(Calendar.SECOND, 1);
		return (T)calendar.getTime();
	}
}
