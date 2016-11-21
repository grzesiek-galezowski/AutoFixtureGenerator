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
  private final CircularList<String> zoneIds;

  public ZoneIdGenerator() {
    final Set<String> availableIds = ZoneId.getAvailableZoneIds();
    zoneIds = new CircularList<>(availableIds.toArray(arrayFor(availableIds)));
  }

  private String[] arrayFor(final Set<String> availableIds) {
    return new String[availableIds.size()];
  }


  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(ZoneId.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) ZoneId.of(zoneIds.next());
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}
