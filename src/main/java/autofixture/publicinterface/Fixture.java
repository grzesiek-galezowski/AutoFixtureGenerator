package autofixture.publicinterface;

import autofixture.publicinterface.generators.GeneratorsFactory;
import autofixture.publicinterface.generators.GeneratorsPipeline;
import autofixture.publicinterface.generators.RecursionGuard;
import autofixture.publicinterface.generators.implementationdetails.ConcreteInstanceType;
import com.google.common.primitives.Primitives;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;

public class Fixture implements FixtureContract {
	private GeneratorsFactory generatorsFactory = new GeneratorsFactory();
    private RecursionGuard recursionGuard = new RecursionGuard(20);
	private GeneratorsPipeline instanceGenerators = generatorsFactory.createBuiltinGenerators(recursionGuard);
    private int repeatCount = 3;

    public <T> T create(Class<T> clazz) {
		return this.create(TypeToken.of(Primitives.wrap(clazz)));
	}

    public <T> T create(TypeToken<T> typeToken) {
		return create(new ConcreteInstanceType<>(typeToken));
	}

    public <T> T freeze(TypeToken<T> clazz) {
        T value = create(clazz);
        inject(value);
        return value;
    }

    public <T> T freeze(Class<T> clazz) {
        return freeze(TypeToken.of(Primitives.wrap(clazz)));
    }

    public void register(InstanceGenerator instanceGenerator) {
		instanceGenerators.registerCustomization(instanceGenerator);
	}

	public void clearCustomizations() {
		instanceGenerators.clearCustomizations();
	}

	public <T> T create(InstanceType<T> instanceType) {
		return instanceGenerators.executeFor(instanceType, this);
	}

    @Override
    public <T> Collection<T> createMany(TypeToken<T> type) {
        ArrayList<T> manyObjects = new ArrayList<T>();

        for(int i = 0 ; i < repeatCount; ++i) {
            manyObjects.add(create(type));
        }
        return manyObjects;
    }

    @Override
    public <T> Collection<? super T> createMany(InstanceType<T> type) {
        return createMany(type.getToken());
    }

    @Override
    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public <T> void inject(final T injectedValue) {
        register(new InstanceGenerator() {
            @Override
            public <T2> boolean appliesTo(InstanceType<T2> instanceType) {
                return instanceType.isSameAsThatOf(injectedValue);
            }

            @Override
            public <T2> T2 next(InstanceType<T2> instanceType, FixtureContract fixture) {
                return (T2)injectedValue;
            }

            @Override
            public void setOmittingAutoProperties(boolean isOn) {

            }
        });
    }

    public <T> T createWith(InlineInstanceGenerator<T> inlineGenerator) {
		return inlineGenerator.next(this);
	}

	public String create(String prefix) {
		return prefix + create(String.class);
	}

	public void setOmittingAutoProperties(boolean isOn) {
		instanceGenerators.setOmittingAutoProperties(isOn);

	}

    public void setRecursionDepth(int depth) {
        this.recursionGuard.setMaxDepth(depth);
    }
}
