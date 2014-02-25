package jfixture.publicinterface.generators.implementationdetails;

import jfixture.publicinterface.InstanceType;

public class CircularList<T> {

	private T[] enumConstants;
	private int currentIndex = 0;

	public static <TListElement> CircularList<TListElement> createFromEnum(InstanceType<TListElement> type) {
		TListElement[] enumConstants = type.getEnumConstants();
		
		return new CircularList<TListElement>(enumConstants);
	}
	
	public CircularList(T[] enumConstants) {
		this.enumConstants = enumConstants;
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

}
