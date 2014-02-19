package jfixture.publicinterface;

public class DoubleGenerator {
	private double startingNumber = 0.3;
	public Double next() {
		return startingNumber++;
	}
}
