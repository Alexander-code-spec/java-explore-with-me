package ru.practicum.stats.server.hit.mapper;

import ru.practicum.stats.dto.StatsDto;
import ru.practicum.stats.server.hit.model.Stats;

public class ViewStatsMapper {
    public static StatsDto toViewStatsDto(Stats viewStats) {
        return StatsDto.builder()
                .hits(viewStats.getHits())
                .uri(viewStats.getUri())
                .app(viewStats.getApp())
                .build();
    }
}