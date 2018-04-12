package benchmarks;

import autofixture.publicinterface.Any;
import autofixture.publicinterface.Fixture;
import autofixture.publicinterface.InstanceOf;
import autofixture.specification.acceptance.testfixtures.DataStructure;
import autofixture.specification.acceptance.testfixtures.GenericDataStructure;
import autofixture.specification.acceptance.testfixtures.GenericInterface;
import autofixture.specification.acceptance.testfixtures.GenericObject;
import autofixture.specification.acceptance.testfixtures.Group;
import autofixture.specification.acceptance.testfixtures.GroupMember;
import autofixture.specification.acceptance.testfixtures.NonGenericInterface;
import autofixture.specification.acceptance.testfixtures.ObjectWithCollectionConstructorParameters;
import autofixture.specification.acceptance.testfixtures.ObjectWithGenericConstructorParameters;
import autofixture.specification.acceptance.testfixtures.ObjectWithPackagePrivateAndPublicConstructor;
import autofixture.specification.acceptance.testfixtures.ObjectWithPackagePrivateConstructor;
import autofixture.specification.acceptance.testfixtures.ObjectWithPrimitiveConstructorParameters;
import autofixture.specification.acceptance.testfixtures.ObjectWithPublicFinalFields;
import autofixture.specification.acceptance.testfixtures.ObjectWithSetters;
import autofixture.specification.acceptance.testfixtures.RecursiveStructure;
import autofixture.specification.acceptance.testfixtures.StructureWithCopyConstructor;
import autofixture.specification.acceptance.testfixtures.ValueObjectWithSingleParameterConstructorAndParameterlessOne;
import org.openjdk.jmh.annotations.Benchmark;

public class Benchmark1 {

    @Benchmark
    public void lol() {
        Fixture o = Any.instanceOf(Fixture.class);
        Any.instanceOf(DataStructure.class);
        Any.anonymous(new InstanceOf<GenericDataStructure<GenericDataStructure<Integer>>>(){});
        Any.anonymous(new InstanceOf<GenericInterface<GenericInterface<Integer>>>(){});
        Any.anonymous(new InstanceOf<GenericObject<GenericObject<Integer>>>(){});
        Any.anonymous(Group.class);
        Any.anonymous(GroupMember.class);
        Any.anonymous(NonGenericInterface.class);
        Any.anonymous(ObjectWithCollectionConstructorParameters.class);
        Any.anonymous(ObjectWithGenericConstructorParameters.class);
        Any.anonymous(ObjectWithPackagePrivateAndPublicConstructor.class);
        Any.anonymous(ObjectWithPackagePrivateConstructor.class);
        Any.anonymous(ObjectWithPrimitiveConstructorParameters.class);
        Any.anonymous(ObjectWithPublicFinalFields.class);
        Any.anonymous(ObjectWithSetters.class);
        Any.anonymous(RecursiveStructure.class);
        Any.anonymous(StructureWithCopyConstructor.class);
        Any.anonymous(ValueObjectWithSingleParameterConstructorAndParameterlessOne.class);
    }
}
