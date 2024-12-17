package helpers;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyTestWatcher implements TestWatcher, BeforeAllCallback, AfterAllCallback {

    private Map<String, List<Method>> statuses;

    private static final String SUCCESS = "s";

    private static final String FAIL = "f";

    @Override
    public void beforeAll(ExtensionContext context) {
        statuses = new HashMap<>();
        statuses.put(SUCCESS, new ArrayList<>());
        statuses.put(FAIL, new ArrayList<>());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        statuses.get(SUCCESS).add(context.getTestMethod().get());
        System.out.println("Успешный тест");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        statuses.get(FAIL).add(context.getTestMethod().get());

        Set<String> tags = context.getTags();
        for (String tag : tags) {
            if (tag.equals("critical")) {
                System.out.println("Упал очень важный тест!!!!!!");
            }
        }
        Tag[] tags1 = context.getTestMethod().get().getAnnotation(Tags.class).value();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("Success " + statuses.get(SUCCESS).size());
        System.out.println("Failed " + statuses.get(FAIL).size());

        String report = "<html><body>";
        report += "Success " + statuses.get(SUCCESS).size() + "<br>";
        report += "Failed " + statuses.get(FAIL).size() + "<br>";
        report += "<br></body></html>";
        Files.writeString(Path.of("report.html"), report);
    }
}
