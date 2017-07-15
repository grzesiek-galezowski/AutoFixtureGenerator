package autofixture.publicinterface;

import autofixture.interfaces.InstanceGenerator;
import autofixture.implementationdetails.CollectionFactory;
import autofixture.generators.objects.implementationdetails.ConcreteInstanceType;
import autofixture.implementationdetails.MapBasedRecursionGuard;
import autofixture.interfaces.*;
import autofixture.generators.DefaultGeneratorsFactory;
import autofixture.interfaces.InlineConstrainedGenerator;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Fixture implements FixtureContract {
  public static final int MINIMUM_VALUE_THAT_COULD_MEAN_MANY = 3;
  private final DefaultGeneratorsFactory generatorsFactory = new DefaultGeneratorsFactory();
  private final RecursionGuard recursionGuard;
  private final GeneratorsPipeline instanceGenerators;
  private final int arbitraryRecursionDepth = 5;
  private final GeneratorsPipeline dummyGenerators;
  private int repeatCount = MINIMUM_VALUE_THAT_COULD_MEAN_MANY;

  public Fixture() {
    dummyGenerators = generatorsFactory.createDummyGenerators();
    recursionGuard = new MapBasedRecursionGuard(arbitraryRecursionDepth, dummyGenerators);
    instanceGenerators = generatorsFactory.createBuiltinGenerators(recursionGuard);
  }

  @Override
  public <T> T create(final Class<T> clazz) {
    return this.create(TypeToken.of(Primitives.wrap(clazz)));
  }

  @Override
  public <T> T create(final TypeToken<T> typeToken) {
    return create(new ConcreteInstanceType<>(typeToken));
  }

  public <T> T create(final InstanceOf<T> typeToken) {
    return create((TypeToken<T>)(typeToken));
  }

  public <T> T createDummy(final Class<T> clazz) {
    return this.createDummy(TypeToken.of(Primitives.wrap(clazz)));
  }

  public <T> T createDummy(final TypeToken<T> typeToken) {
    return createDummy(new ConcreteInstanceType<>(typeToken));
  }

  public <T> T createDummy(final InstanceOf<T> typeToken) {
    return createDummy((TypeToken<T>)(typeToken));
  }

  @Override
  public <T> T freeze(final TypeToken<T> clazz) {
    final T value = create(clazz);
    inject(value);
    return value;
  }

  public <T> T freeze(final InstanceOf<T> clazz) {
    return freeze((TypeToken<T>)clazz);
  }

  @Override
  public <T> T freeze(final Class<T> clazz) {
    return freeze(TypeToken.of(Primitives.wrap(clazz)));
  }

  @Override
  public void register(final InstanceGenerator instanceGenerator) {
    instanceGenerators.registerCustomization(instanceGenerator);
  }

  @Override
  public void clearCustomizations() {
    instanceGenerators.clearCustomizations();
  }

  @Override
  public <T> T create(final InstanceType<T> instanceType) {
    return instanceGenerators.executeFor(instanceType, this);
  }

  public <T> T createDummy(final InstanceType<T> instanceType) {
    return dummyGenerators.executeFor(instanceType, this);
  }

  @Override
  public <T> Collection<T> createMany(final TypeToken<T> type) {
    final List<T> manyObjects = Arrays.asList(createArray(type));

    return manyObjects;
  }

  public <T> Collection<T> createMany(final InstanceOf<T> type) {
    return createMany((TypeToken<T>)type);
  }

  @Override
  public <T> T[] createArray(final TypeToken<T> type) {
    final T[] array = CollectionFactory.createArray(type, repeatCount);
    for (int i = 0; i < repeatCount; ++i) {
      Array.set(array, i, create(type));
    }
    return array;
  }

  public <T> T[] createArray(final InstanceOf<T> type) {
    return createArray((TypeToken<T>)type);
  }

  @Override
  public <T> Collection<? super T> createMany(final InstanceType<T> type) {
    return createMany(type.getToken());
  }

  @Override
  public int getRepeatCount() {
    return repeatCount;
  }

  public void setRepeatCount(final int repeatCount) {
    this.repeatCount = repeatCount;
  }

  @Override
  public <T> void inject(final T injectedValue) {
    register(new InstanceGenerator() {
      @Override
      public <T2> boolean appliesTo(final InstanceType<T2> instanceType) {
        return instanceType.isSameAsThatOf(injectedValue);
      }

      @Override
      public <T2> T2 next(final InstanceType<T2> instanceType, final FixtureContract fixture) {
        return (T2) injectedValue;
      }

      @Override
      public void setOmittingAutoProperties(final boolean isOn) {

      }
    });
  }

  @Override
  public <T> T create(final TypeToken<T> type, final InlineConstrainedGenerator<T> generator) {
    return generator.next(type, this);
  }

  public <T> T create(final InstanceOf<T> type, final InlineConstrainedGenerator<T> generator) {
    return create((TypeToken<T>)type, generator);
  }

  @Override
  public <T> T create(final InlineInstanceGenerator<T> inlineGenerator) {
    return inlineGenerator.next(this);
  }

  public String create(final String prefix) {
    return prefix + create(String.class);
  }

  public void setOmittingAutoProperties(final boolean isOn) {
    instanceGenerators.setOmittingAutoProperties(isOn);

  }

  public void setRecursionDepth(final int depth) {
    this.recursionGuard.setMaxDepth(depth);
  }
}
