

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner2 {
    public static void main(String[] args) {
        Result Result = JUnitCore.runClasses(AmExVerificationHandlerTest.class,
                DiscoverVerificationHandlerTest.class);

        for (Failure failure : Result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(Result.wasSuccessful());
        System.out.println(Result.getFailureCount());

    }
}
