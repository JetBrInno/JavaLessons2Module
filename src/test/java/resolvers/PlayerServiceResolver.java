package resolvers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

public class PlayerServiceResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        createFile();
        return new PlayerServiceImpl();
    }

    private void createFile(){
        try {
            Files.writeString(Path.of("./data.json"),"[]");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
