package autofixture.publicinterface;

import com.google.common.reflect.TypeToken;

import java.util.Collection;

public interface FixtureContract {
  <T> T create(Class<T> clazz);

  <T> T create(TypeToken<T> typeToken);

  <T> T freeze(TypeToken<T> clazz);

  <T> T freeze(Class<T> clazz);

  void register(InstanceGenerator instanceGenerator);

  void clearCustomizations();

  <T> T create(InstanceType<T> instanceType);

  <T> Collection<? super T> createMany(InstanceType<T> type);

  <T> Collection<T> createMany(TypeToken<T> type);

  int getRepeatCount();

  <T> void inject(T injectedValue);

  <T> T create(TypeToken<T> type, InlineConstrainedGenerator<T> generator);
}
