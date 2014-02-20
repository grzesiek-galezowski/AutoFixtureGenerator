package jfixture.specification;

import jfixture.publicinterface.Fixture;
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
	public void ShouldGenerateCorrectCollectionsWithThreeElements() {
		String[] array = fixture.create(String[].class);
		
		assertThat(array, hasLength(3));
		assertThat(array[0], is(not(array[1])));
		assertThat(array[0], is(not(array[2])));
		assertThat(array[1], is(not(array[2])));
	}

	private Matcher<? super String[]> hasLength(int i) {
		return new HasLengthMatcher(i);
	}

}
