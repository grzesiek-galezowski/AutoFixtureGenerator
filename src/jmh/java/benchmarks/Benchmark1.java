package benchmarks;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import org.openjdk.jmh.annotations.Benchmark;

public class Benchmark1 {

    @Benchmark
    public void lol() {
        Fixture o = Any.instanceOf(Fixture.class);
    }
}
