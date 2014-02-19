package jfixture.publicinterface;

public class IntGenerator {

	public int startingInteger = 1;
	public Integer next() {
		return startingInteger++;
	}

}
