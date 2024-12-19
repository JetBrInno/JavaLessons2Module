

import static org.assertj.core.api.Assertions.assertThat;

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
public class PlayerServiceAssertJTest {

    @Test
    @DisplayName("Открытие пустого файла")
    public void canOpenEmptyFile(@Players(3)PlayerService service, CatalogService catalogService) {
        System.out.println("тест");
        Collection<Player> players = service.getPlayers();
    }

    @Test
    @DisplayName("Открытие файла с 100 пользователей")
    public void canOpenFilledFile(@Players(5)PlayerService service) {
        Collection<Player> players = service.getPlayers();
       // assertThat(players).contains(new Player(123,"Name", 190,true));
        assertThat(players).allMatch(p -> p.isOnline() == true);
    }

    @Test
    public void numberTest() {
        int number = -100;
        assertThat(number).isPositive().isBetween(-1000,1000);
    }
}
