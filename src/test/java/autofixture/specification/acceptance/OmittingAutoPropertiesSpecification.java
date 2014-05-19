package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.DataStructure;
import autofixture.specification.acceptance.testfixtures.GenericDataStructure;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class OmittingAutoPropertiesSpecification {

    private Fixture fixture = new Fixture();
    
    @Test
    public void shouldNotSetPropertiesAndPublicFieldsWhenAutoPropertiesAreOmitted()
    {
      //GIVEN
      fixture.setOmittingAutoProperties(true);

      //WHEN
      DataStructure data = fixture.create(DataStructure.class);
      
      //THEN
      assertThat(data.getY(), equalTo(0));
      assertThat(data.z, equalTo(0));
    }
    
    @Test
    public void shouldSetPropertiesAndPublicFieldsWhenAutoPropertiesAreNotOmitted()
    {
      //GIVEN
      fixture.setOmittingAutoProperties(true);
      fixture.setOmittingAutoProperties(false);

      //WHEN
      DataStructure data = fixture.create(DataStructure.class);
      
      //THEN
      assertThat(data.getY(), not(equalTo(0)));
      assertThat(data.z, not(equalTo(0)));
    }

    @Test
    public void shouldNotSetGenericPropertiesAndPublicFieldsWhenAutoPropertiesAreOmitted()
    {
      //GIVEN
      fixture.setOmittingAutoProperties(true);

      //WHEN
      GenericDataStructure<Integer> data 
      	= fixture.create(new InstanceOf<GenericDataStructure<Integer>>() {});
      
      //THEN
      assertThat(data.getY(), equalTo(null));
      assertThat(data.z, equalTo(null));
    }
    
    @Test
    public void shouldSetGenericPropertiesAndPublicFieldsWhenAutoPropertiesAreNotOmitted()
    {
      //GIVEN
      fixture.setOmittingAutoProperties(false);

      //WHEN
      GenericDataStructure<Integer> data 
    	= fixture.create(new InstanceOf<GenericDataStructure<Integer>>() {});
      
      //THEN
      assertThat(data.getY(), not(equalTo(0)));
      assertThat(data.z, not(equalTo(0)));
    }
}


