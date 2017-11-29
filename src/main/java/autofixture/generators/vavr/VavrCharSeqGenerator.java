package autofixture.generators.vavr;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import com.google.common.reflect.TypeToken;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;

public class VavrCharSeqGenerator implements InstanceGenerator {

  @Override
  public <T> boolean appliesTo(final InstanceType<T> instanceType) {
    return instanceType.isRawTypeAssignableFrom(io.vavr.collection.CharSeq.class);
  }

  @Override
  public <T> T next(final InstanceType<T> instanceType, final FixtureContract fixture) {
    return (T) CharSeq.ofAll(fixture.createMany(TypeToken.of(Character.class)));
  }

  @Override
  public void setOmittingAutoProperties(final boolean isOn) {

  }
}

