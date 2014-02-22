package jfixture.specification.testfixtures;


public class ObjectWithGenericConstructorParameters {

	public GenericObject<String> parameter1;
	//public GenericObject<Integer> parameter2;
	//public GenericObject<GenericObject<String>> parameter3;

	public ObjectWithGenericConstructorParameters(
			GenericObject<String> parameter1
			//GenericObject<Integer> parameter2,
			//GenericObject<GenericObject<String>> parameter3) {
			){
		this.parameter1 = parameter1;
		//this.parameter2 = parameter2;
		//this.parameter3 = parameter3;
	}	
}
