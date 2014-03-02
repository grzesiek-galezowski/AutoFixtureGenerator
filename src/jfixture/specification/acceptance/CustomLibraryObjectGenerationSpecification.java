package jfixture.specification.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;

import jfixture.publicinterface.Fixture;
import jfixture.specification.acceptance.matchers.HasArrayLengthMatcher;
import jfixture.specification.acceptance.testfixtures.ObjectWithCollectionConstructorParameters;
import jfixture.specification.acceptance.testfixtures.ObjectWithGenericConstructorParameters;
import jfixture.specification.acceptance.testfixtures.ObjectWithPrimitiveConstructorParameters;
import jfixture.specification.acceptance.testfixtures.ObjectWithSetters;

import org.junit.Test;

import com.google.common.reflect.TypeToken;

public class CustomLibraryObjectGenerationSpecification {

	Fixture fixture = new Fixture();
	
	@Test
	public void shouldCreateValidAndDifferentUris() {
		URI uri1 = fixture.create(URI.class);
		URI uri2 = fixture.create(URI.class);
		
		assertThat(uri1, is(notNullValue()));
		assertThat(uri2, is(notNullValue()));
		assertThat(uri1, is(not(uri2)));
	}

	@Test
	public void shouldCreateValidAndDifferentUrls() throws MalformedURLException {
		URL url1 = fixture.create(URL.class);
		URL url2 = fixture.create(URL.class);
		
		assertThat(url1, is(notNullValue()));
		assertThat(url2, is(notNullValue()));
		assertThat(url1, is(not(url2)));
	}
	
	@SafeVarargs
	static <E> E[] array(E... array)
	{
	    return array;
	}
	
}
