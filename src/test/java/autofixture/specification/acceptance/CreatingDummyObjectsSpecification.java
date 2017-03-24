package autofixture.specification.acceptance;

import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by grzes on 23.03.2017.
 */
public class CreatingDummyObjectsSpecification {
  private Fixture fixture = new Fixture();

  @Test
  public void shouldGenerateEmptyCollections() {
    List<Integer> list = fixture.createDummy(new InstanceOf<List<Integer>>() {});
    Map<Integer, Integer> map = fixture.createDummy(new InstanceOf<Map<Integer, Integer>>(){});

    assertThat(list, hasSize(equalTo(0)));
    assertThat(map.keySet(), hasSize(equalTo(0)));
    assertThat(map.values(), hasSize(equalTo(0)));
  }
}
