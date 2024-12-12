package helpers;

import java.util.Set;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Set<String> tags = context.getTags();
        for (String tag : tags) {
            if (tag.equals("critical")) {
                System.out.println("Упал очень важный тест!!!!!!");
            }
        }
    }
}
