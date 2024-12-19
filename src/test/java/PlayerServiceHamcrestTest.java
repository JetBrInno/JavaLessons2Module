import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import helpers.MyTestWatcher;
import helpers.Players;
import java.util.Collection;
import org.hamcrest.Matchers;
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
public class PlayerServiceHamcrestTest {

    @Test
    @DisplayName("Открытие пустого файла")
    public void canOpenEmptyFile(@Players(3)PlayerService service, CatalogService catalogService) {
        System.out.println("тест");
        Collection<Player> players = service.getPlayers();
        //assertThat(players, hasSize(100));
        assertThat(players, hasItem(new Player(124,"dasa",100,true)));
       // assertEquals(0, players.size());
    }

    @Test
    @DisplayName("Открытие файла с 100 пользователей")
    public void canOpenFilledFile(@Players(100)PlayerService service) {
        Collection<Player> players = service.getPlayers();

       // assertEquals(99, players.size(), "Проверка количества пользователей");
      //  assertTrue(players.size() == 33);
    }

    @Test
    public void numberTest() {
        int number = 100;
        assertThat(number, not(equalTo(50)));
        assertThat(number, not(lessThan(50)));

        assertThat(number, anyOf(
            not(equalTo(50)),
            not(lessThan(500))
        ));
    }
}
