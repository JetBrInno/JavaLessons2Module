import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import ru.inno.course.player.model.Player;
import ru.inno.course.player.service.PlayerService;
import ru.inno.course.player.service.PlayerServiceImpl;

public class AddPlayerTest {

    private final String playerName = "Jack";

    @Test
    public void canCreateUser() throws IOException {
        Files.deleteIfExists(Path.of("./data.json")); // предусловие

        PlayerService service = new PlayerServiceImpl();
        int playerId = service.createPlayer(playerName);
        Player player = service.getPlayerById(playerId);
        assertEquals(player.getId(), playerId);
        assertEquals(playerName, player.getNick());
        assertTrue(player.isOnline());
    }

    // 1. Тесты должны быть независимы друг от друга
    // 2. Слишком много однотипн. дублир. кода (типо try catch)
    // 3. Нечитабельные проверки

    @Test
    public void cantCreateDuplicatePlayerName() throws IOException {
        Files.deleteIfExists(Path.of("./data.json")); // предусловие
        PlayerService service = new PlayerServiceImpl(); // предусловие
        service.createPlayer(playerName); // предусловие

        assertThrows(IllegalArgumentException.class, () -> service.createPlayer(playerName));
    }
}
