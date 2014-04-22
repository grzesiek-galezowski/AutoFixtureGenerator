package autofixture.publicinterface.generators;

import autofixture.publicinterface.InstanceType;

public interface CachedSequences {
	<T> boolean containSequenceFor(InstanceType<T> type);
	<T> void addSequenceForType(InstanceType<T> type);
	<T> T retrieveNextValueOf(InstanceType<T> type);
}
