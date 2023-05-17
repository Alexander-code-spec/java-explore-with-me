package ru.practicum.stats.server.hit.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stats.server.hit.model.Hit;
import ru.practicum.stats.server.hit.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface HitStorage extends JpaRepository<Hit, Integer> {

    @Query(nativeQuery = true, name = "FindStatsWithUriAndNotUniqueIp")
    List<Stats> getStatsWithNotUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(nativeQuery = true, name = "FindStatsWithUriAndUniqueIp")
    List<Stats> getStatsWithUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(nativeQuery = true, name = "FindAllStatsWithNotUniqueIp")
    List<Stats> getAllStatsWithNotUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query(nativeQuery = true, name = "FindAllStatsWithUniqueIp")
    List<Stats> getAllStatsWithUniqueIp(LocalDateTime start, LocalDateTime end);
}
