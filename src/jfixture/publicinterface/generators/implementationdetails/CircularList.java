package jfixture.publicinterface.generators.implementationdetails;

import jfixture.publicinterface.InstanceType;

public class CircularList<T> {

	private T[] enumConstants;
	private int currentIndex = 0;

	public static <TListElement> CircularList<TListElement> createFromEnum(InstanceType<TListElement> type) {
		TListElement[] enumConstants = type.getEnumConstants();
		
		return new CircularList<TListElement>(enumConstants);
	}
	
	public CircularList(T[] values) {
		this.enumConstants = values;
		currentIndex = 0;
	}
	
	public T next() {
		if(currentIndex >= enumConstants.length) {
			currentIndex = 0;
		}
		
		T nextElement = enumConstants[currentIndex];
		currentIndex++;
		return nextElement;
	}

	public static CircularList<Character> fromCharactersIn(String string) {
		Character[] characters = toCharacterArray(string); 
		return new CircularList<Character>(characters);
	}

	
	private static Character[] toCharacterArray(String s) {
		   if (s == null) {
		     return null;
		   }
		   Character[] array = new Character[s.length()];
		   for (int i = 0; i < s.length(); i++) {
		      array[i] = new Character(s.charAt(i));
		   }

		   return array;
		}
}
