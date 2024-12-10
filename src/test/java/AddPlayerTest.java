import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

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
    public void cantCreateDuplicatePlayerName() {
        assertThrows(IllegalArgumentException.class, () -> service.createPlayer(playerName));
    }
}
