package ru.practicum.hit.storage;

import ru.practicum.hit.entity.Stats;
import java.time.LocalDateTime;
import java.util.List;

public interface HitStorage {

    List<Stats> getStats(LocalDateTime start,
                             LocalDateTime end,
                             List<String> uris,
                             boolean unique);
}
