import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import helpers.MyTestWatcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import resolvers.PlayerServiceResolver;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

@ExtendWith(MyTestWatcher.class)
@ExtendWith(PlayerServiceResolver.class)
public class AddPlayerTest {

    private final String playerName = "Jack";

    private PlayerService service;

    private int playerId;

    @BeforeEach
    public void setUp() {
        service = new PlayerServiceImpl();
        playerId = service.createPlayer(playerName);
        System.out.println("before each");
    }

    @BeforeAll
    public static void setUpGlobal(){
        System.out.println("before all");
    }

    @AfterAll
    public static void tearDownGlobal(){
        System.out.println("after all");
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Path.of("./data.json"));
        System.out.println("after each");
    }

    @Test
    @DisplayName("Создание пользователя с валидными данными")
    public void canCreateUser() {
        Player player = service.getPlayerById(playerId);
        assertEquals(player.getId(), playerId);
        assertEquals(playerName, player.getNick());
        assertTrue(player.isOnline());
    }

    // 1. Тесты должны быть независимы друг от друга
    // 2. Слишком много однотипн. дублир. кода (типо try catch)
    // 3. Нечитабельные проверки

    @Test
    @Tag("negative")
    @DisplayName("Дублирование пользователя невозможно")
    @Disabled("Тест временно выключен из-за бага (jira.com/task-5152)")
    public void cantCreateDuplicatePlayerName() {
        assertThrows(IllegalArgumentException.class, () -> service.createPlayer(playerName));
    }

    @DisplayName("Добавление очков пользователю")
    @ParameterizedTest
    @Tags({@Tag("positive"), @Tag("critical")})
    @ValueSource(ints = {1, 50, 99, 105})
    public void canAddPointsToUser(int scoreToAdd) {
        System.out.println("Это тест про очки");
        service.addPoints(playerId, scoreToAdd);
        int points = service.getPlayerById(playerId).getPoints();
        assertEquals(scoreToAdd, points);
    }
}
