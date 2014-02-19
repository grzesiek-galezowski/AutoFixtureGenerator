package jfixture.publicinterface;

import java.util.UUID;

public class StringGenerator {
	String next() {
		return UUID.randomUUID().toString();
	}
	
}
