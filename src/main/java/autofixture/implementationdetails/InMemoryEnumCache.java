package autofixture.implementationdetails;

import autofixture.interfaces.InstanceType;
import autofixture.interfaces.EnumCache;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEnumCache implements EnumCache {

  private final Map<InstanceType<?>, CircularList<?>> sequences = new HashMap<>();

  @Override
  public <T> void registerIfNotPresent(final InstanceType<T> instanceType) {
    if (!sequences.containsKey(instanceType)) {
      sequences.put(instanceType, CircularList.createFromEnum(instanceType));
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T retrieveNextValueOf(final InstanceType<T> instanceType) {
    return (T) sequences.get(instanceType).next();
  }
}
