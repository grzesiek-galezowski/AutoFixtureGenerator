package autofixture.interfaces;

import com.google.common.reflect.TypeToken;

import java.util.Collection;

public interface FixtureContract {
  <T> T create(Class<T> clazz);

  <T> T create(TypeToken<T> typeToken);

  <T> T create(InstanceType<T> instanceType);

  <T> Collection<? super T> createMany(InstanceType<T> type);

  <T> T createDummy(InstanceType<T> instanceType);

  <T> Collection<T> createMany(TypeToken<T> type);

  <T> T[] createArray(TypeToken<T> type);

  int getRepeatCount();

  <T> T create(InlineInstanceGenerator<T> generator);
}
