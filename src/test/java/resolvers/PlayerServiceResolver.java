package resolvers;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.PlayerGenerator;
import helpers.Players;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationHelper;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

public class PlayerServiceResolver implements ParameterResolver, AfterEachCallback {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {
        Players annotation = AnnotationHelper.findAnnotation(parameterContext.getAnnotatedElement(), Players.class);
        int num = annotation.value();
        Set<Player> players = PlayerGenerator.generate(num);

        createFile(players);
        return new PlayerServiceImpl();
    }

    private void createFile(Set<Player> players){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(Path.of("./data.json").toFile(), players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Files.deleteIfExists(Path.of("./data.json"));
    }
}
