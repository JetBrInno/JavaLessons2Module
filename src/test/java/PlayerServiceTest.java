import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import helpers.MyTestWatcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import resolvers.CatalogServiceResolver;
import resolvers.PlayerServiceResolver;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.CatalogService;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

@ExtendWith(MyTestWatcher.class)
@ExtendWith({PlayerServiceResolver.class, CatalogServiceResolver.class})
public class PlayerServiceTest {

//    @AfterEach
//    public void tearDown() throws IOException {
//        Files.deleteIfExists(Path.of("./data.json"));
//    }

    @Test
    @DisplayName("Открытие пустого файла")
    public void canOpenEmptyFile(PlayerService service, CatalogService catalogService) {
        System.out.println("тест");
        Collection<Player> players = service.getPlayers();
        assertEquals(0, players.size());
    }

    @Test
    @DisplayName("Открытие файла с 100 пользователей")
    public void canOpenFilledFile(PlayerService service) {
        // добавь 100 пользователей
        Collection<Player> players = service.getPlayers();
        assertEquals(0, players.size());
    }
}
