package jfixture.specification.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import jfixture.publicinterface.Fixture;
import jfixture.publicinterface.InstanceOf;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.google.common.reflect.TypeToken;

@RunWith(Theories.class)
public class CustomLibraryObjectGenerationSpecification {

	Fixture fixture = new Fixture();
	
	@Theory
	public void shouldGenerateDifferentValuesEachTime(Class<?> clazz) {
		System.out.println(clazz);
		
		Object value1 = fixture.create(TypeToken.of(clazz));
		Object value2 = fixture.create(TypeToken.of(clazz));
		
		assertThat(value1, is(notNullValue()));
		assertThat(value2, is(notNullValue()));
        assertThat(value1, is(not(equalTo(value2))));
	}
	

	@DataPoint
	public static Class<URI> uriClass = URI.class;
	@DataPoint
	public static Class<URL> urlClass = URL.class;
	@DataPoint
	public static Class<InetAddress> ipClass = InetAddress.class;
	
	@SafeVarargs
	static <E> E[] array(E... array)
	{
	    return array;
	}
	
}
