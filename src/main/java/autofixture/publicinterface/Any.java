package autofixture.publicinterface;

import static autofixture.publicinterface.Generate.any;

public class Any
{
    public static <T> T instance() {
        return any(new InstanceOf<T>() {});
    }
}
