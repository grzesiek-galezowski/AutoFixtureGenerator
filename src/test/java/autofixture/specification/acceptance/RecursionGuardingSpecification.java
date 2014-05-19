package autofixture.specification.acceptance;
import autofixture.publicinterface.Fixture;
import autofixture.specification.acceptance.testfixtures.RecursiveStructure;
import org.junit.Test;

public class RecursionGuardingSpecification {
    private Fixture fixture = new Fixture();

    @Test
    public void shouldReturnNullAsRecursiveObjectWhenItsRecursionIsFinished() {
        RecursiveStructure instance = fixture.create(RecursiveStructure.class);

    }
}
