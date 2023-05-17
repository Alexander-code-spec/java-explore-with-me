package ru.practicum.stats.server.hit.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stats.server.hit.model.Hit;
import ru.practicum.stats.server.hit.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface HitStorage extends JpaRepository<Hit, Integer> {
    @Query(value = ""
            + "SELECT NEW ru.practicum.stats.server.hit.model.Stats(hit.uri, hit.app, COUNT(hit.ip)) "
            + "FROM Hit hit "
            + "WHERE hit.uri IN ?3 AND hit.timestamp BETWEEN ?1 AND ?2 "
            + "GROUP BY hit.uri, hit.app "
            + "ORDER BY COUNT(hit.ip) DESC")
    List<Stats> getEndpointHits(LocalDateTime start,
                                LocalDateTime end,
                                List<String> uris);

    @Query(value = ""
            + "SELECT NEW ru.practicum.stats.server.hit.model.Stats(hit.uri, hit.app, COUNT(DISTINCT hit.ip)) "
            + "FROM Hit hit "
            + "WHERE hit.uri IN ?3 AND hit.timestamp BETWEEN ?1 AND ?2 "
            + "GROUP BY hit.uri, hit.app "
            + "ORDER BY COUNT(DISTINCT hit.ip) DESC")
    List<Stats> getDistinctEndpointHits(LocalDateTime start,
                                            LocalDateTime end,
                                            List<String> uris);
}
