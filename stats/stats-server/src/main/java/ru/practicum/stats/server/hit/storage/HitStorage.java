package ru.practicum.stats.server.hit.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stats.server.hit.model.Hit;
import ru.practicum.stats.server.hit.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface HitStorage extends JpaRepository<Hit, Integer> {

    @Query("SELECT new ru.practicum.stats.server.hit.model.Stats(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Hits AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip) DESC")
    List<Stats> getAllStatsDistinctIp(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.stats.server.hit.model.Stats(s.app, s.uri, COUNT(s.ip)) " +
            "FROM Hits AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.ip) DESC")
    List<Stats> getAllStats(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.stats.server.hit.model.Stats(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Hits AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "AND s.uri IN (?3) " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip) DESC")
    List<Stats> getStatsByUrisDistinctIp(LocalDateTime start, LocalDateTime end, List<String> uri);

    @Query("SELECT new ru.practicum.stats.server.hit.model.Stats(s.app, s.uri, COUNT(s.ip)) " +
            "FROM Hits AS s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "AND s.uri IN (?3) " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.ip) DESC")
    List<Stats> getStatsByUris(LocalDateTime start, LocalDateTime end, List<String> uri);
}
