package jfixture.specification;

import java.util.ArrayList;
import java.util.Collection;
import com.google.common.reflect.*;

import jfixture.publicinterface.Fixture;
import jfixture.specification.matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Test;

public class CollectionTypesGenerationSpecification {

	/*
	 TODO
	 Array
	 Collection
	   Set
	     SortedSet
	   List
	   Queue
	   Deque
	   SortedSet
	   Map
	     SortedMap
	     
	  TODO generic versions
	  TODO non-generic versions
	  TODO collections of collections!
	 */
	
	Fixture fixture = new Fixture();
	
	@Test
	public void ShouldGenerateArraysWithThreeUniqueElements() {
		String[] array = fixture.create(TypeToken.of(String[].class));
		
		assertThat(array, hasLength(3));
		assertThat(array, hasUniqueItems());
	}
	
	@Test
	public void ShouldGenerateCollectionsWithThreeUniqueElements() {
		 
		Collection<String> collection = fixture.create(new TypeToken<Collection<String>>() {});
		
		assertThat(collection.toArray(TypeOfString()), hasLength(3));
		assertThat(collection.toArray(TypeOfString()), hasUniqueItems());
	}

	@Test
	public void ShouldGenerateArrayListWithThreeUniqueElements() {
		 
		ArrayList<String> collection = fixture.create(new TypeToken<ArrayList<String>>() {});
		
		assertThat(collection.toArray(TypeOfString()), hasLength(3));
		assertThat(collection.toArray(TypeOfString()), hasUniqueItems());
	}
	
	
	private String[] TypeOfString() {
		return new String[]{};
	}
	

	private Matcher<? super String[]> hasUniqueItems() {
		return new HasArrayUniqueItemsMatcher();
	}

	private Matcher<? super String[]> hasLength(int i) {
		return new HasArrayLengthMatcher(i);
	}

	
	
}
