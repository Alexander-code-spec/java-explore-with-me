package ru.practicum.hit.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.hit.exception.StatsBadTimeException;
import ru.practicum.hit.mapper.HitMapper;
import ru.practicum.hit.mapper.StatsMapper;
import ru.practicum.hit.storage.StatsStorage;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.dto.StatsDto;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class HitServiceImpl implements HitService {
    private final StatsMapper viewStatsMapper;
    private final StatsStorage statsRepository;
    private final HitMapper hitMapper;

    @Override
    @Transactional
    public void saveHit(HitDto hitDto) {
        statsRepository.save(hitMapper.toEntity(hitDto));
    }

    @Override
    public List<StatsDto> getHits(LocalDateTime start,
                                  LocalDateTime end,
                                  List<String> uris,
                                  boolean unique) {
        if (start.isAfter(LocalDateTime.now()) || end.isBefore(LocalDateTime.now()))
            throw new StatsBadTimeException("Incorrect start or end time");
        return statsRepository.getStats(start, end, uris, unique)
                .stream()
                .map(viewStatsMapper::toDto)
                .collect(toList());
    }
}
