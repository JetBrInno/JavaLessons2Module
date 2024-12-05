package ru.inno.course.player.data;

import java.io.IOException;
import java.util.Collection;
import ru.inno.course.player.model.Player;

public interface DataProvider {

    void save(Collection<Player> players) throws IOException;

    Collection<Player> load() throws IOException;
}
