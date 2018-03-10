package autofixture.generators;

import autofixture.interfaces.FixtureContract;
import autofixture.interfaces.InstanceGenerator;
import autofixture.interfaces.InstanceType;
import com.google.common.collect.Lists;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static autofixture.publicinterface.InlineGenerators.alphaString;

public class PathGenerator implements InstanceGenerator {

    private Optional<Path> root = Lists.newArrayList(FileSystems.getDefault().getRootDirectories())
        .stream()
        .findFirst();

    @Override
    public <T> boolean appliesTo(InstanceType<T> instanceType) {
        return instanceType.isRawTypeAssignableFrom(Path.class);
    }

    @Override
    public <T> T next(InstanceType<T> instanceType, FixtureContract fixture) {
        return (T)Paths.get(
            root.get().toString(),
            fixture.create(alphaString()),
            fixture.create(alphaString()),
            fixture.create(alphaString()));
    }

    @Override
    public <T> T nextEmpty(InstanceType<T> instanceType, FixtureContract fixture) {
        return (T)root.get();
    }

    @Override
    public void setOmittingAutoProperties(boolean isOn) {

    }
}
