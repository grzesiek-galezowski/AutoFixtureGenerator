package autofixture.specification.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import autofixture.publicinterface.Fixture;
import autofixture.specification.acceptance.testfixtures.DataStructure;

public class OmitingAutoPropertiesSpecification {

    @Test
    public void shouldNotSetPropertiesAndPublicFieldsWhenAutoPropertiesAreOmitted()
    {
      //GIVEN
      Fixture fixture = new Fixture();
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
      Fixture fixture = new Fixture();
      fixture.setOmittingAutoProperties(false);

      //WHEN
      DataStructure data = fixture.create(DataStructure.class);
      
      //THEN
      assertThat(data.getY(), not(equalTo(0)));
      assertThat(data.z, not(equalTo(0)));
    }
    
    //TODO test for generic structures as well!!!
}


