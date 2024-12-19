import static org.junit.jupiter.api.Assertions.assertEquals;

import helpers.MyTestWatcher;
import helpers.Players;
import java.util.Collection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import resolvers.CatalogServiceResolver;
import resolvers.PlayerServiceResolver;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.CatalogService;
import ru.inno.course.player.service.PlayerService;

@ExtendWith(MyTestWatcher.class)
@ExtendWith({PlayerServiceResolver.class, CatalogServiceResolver.class})
public class PlayerServiceTest {

    @Test
    @DisplayName("Открытие пустого файла")
    public void canOpenEmptyFile(@Players(0)PlayerService service, CatalogService catalogService) {
        System.out.println("тест");
        Collection<Player> players = service.getPlayers();
        assertEquals(0, players.size());
    }

    @Test
    @DisplayName("Открытие файла с 100 пользователей")
    public void canOpenFilledFile(@Players(100)PlayerService service) {
        Collection<Player> players = service.getPlayers();
        assertEquals(100, players.size());
    }
}
