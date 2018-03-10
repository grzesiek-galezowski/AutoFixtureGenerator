package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InlineGeneratorsFactory;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import com.google.common.collect.Lists;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class PathGenerator implements InstanceGenerator {
    private InlineGeneratorsFactory generatorsFactory;
    private Optional<Path> root;

    public PathGenerator(InlineGeneratorsFactory generatorsFactory) {

        this.generatorsFactory = generatorsFactory;
        root = Lists.newArrayList(FileSystems.getDefault().getRootDirectories())
            .stream()
            .findFirst();
    }

    @Override
    public <T> boolean appliesTo(InstanceType<T> instanceType) {
        return instanceType.isAssignableTo(Path.class);
    }

    @Override
    public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
        return (T)Paths.get(
            root.get().toString(),
            fixture.create(generatorsFactory.alphaString()),
            fixture.create(generatorsFactory.alphaString()),
            fixture.create(generatorsFactory.alphaString()));
    }

    @Override
    public <T> T nextEmpty(InstanceType<T> instanceType, FixtureContract fixture) {
        return (T)root.get();
    }

    @Override
    public void setOmittingAutoProperties(boolean isOn) {

    }
}
