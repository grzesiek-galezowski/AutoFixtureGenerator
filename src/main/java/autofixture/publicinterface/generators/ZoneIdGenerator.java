package autofixture.publicinterface.generators;

import autofixture.publicinterface.FixtureContract;
import autofixture.publicinterface.InstanceGenerator;
import autofixture.publicinterface.InstanceType;
import autofixture.publicinterface.generators.implementationdetails.CircularList;

import java.time.ZoneId;
import java.util.Set;

/**
 * Created by astral on 08.02.15.
 */
public class ZoneIdGenerator implements InstanceGenerator {
  private CircularList<String> zoneIds;

  public ZoneIdGenerator() {
    Set<String> availableIds = ZoneId.getAvailableZoneIds();
    zoneIds = new CircularList<>(availableIds.toArray(arrayFor(availableIds)));
  }

  private String[] arrayFor(Set<String> availableIds) {
    return new String[availableIds.size()];
  }


  @Override
  public <T> boolean appliesTo(InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ZoneId.class);
  }

  @Override
  public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
    return (T)ZoneId.of(zoneIds.next());
  }

  @Override
  public void setOmittingAutoProperties(boolean isOn) {

  }
}
