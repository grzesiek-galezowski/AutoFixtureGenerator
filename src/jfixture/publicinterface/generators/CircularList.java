package jfixture.publicinterface.generators;

import jfixture.publicinterface.InstanceType;

public class CircularList<T> {

	private T[] enumConstants;
	private int currentIndex = 0;

	public static <TListElement> CircularList<TListElement> createFromEnum(InstanceType<TListElement> type) {
		Object[] enumConstants = type.getEnumConstants();
		
		return new CircularList<TListElement>(enumConstants);
	}
	
	@SuppressWarnings("unchecked")
	public CircularList(Object[] enumConstants) {
		this.enumConstants = (T[]) enumConstants;
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
