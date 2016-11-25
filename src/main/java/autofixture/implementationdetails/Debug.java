package autofixture.implementationdetails;

/**
 * Created by grzes on 25.11.2016.
 */
public class Debug {
    public static void debugPrint(Exception t) {
      if(System.getenv("AUTOFIXTUREGENERATOR_DEBUG") != null) {
        System.out.println(t);
      }
    }
}
