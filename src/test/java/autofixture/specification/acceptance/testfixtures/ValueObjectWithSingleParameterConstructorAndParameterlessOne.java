package autofixture.specification.acceptance.testfixtures;

import com.google.common.base.Objects;

public class ValueObjectWithSingleParameterConstructorAndParameterlessOne {
	private int field;

	public ValueObjectWithSingleParameterConstructorAndParameterlessOne() {
		field = 123;
	}
	
	public ValueObjectWithSingleParameterConstructorAndParameterlessOne(int fieldValue) {
		field = fieldValue;
	}
	
	@Override
	public boolean equals(Object another) {
		return this.field == 
				((ValueObjectWithSingleParameterConstructorAndParameterlessOne)another).field;

	}
	
	@Override
	public String toString() {
		return "Value Object with field " + field;
	}
	
	
}
