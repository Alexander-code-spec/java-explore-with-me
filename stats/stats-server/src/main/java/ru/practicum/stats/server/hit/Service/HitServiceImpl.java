package ru.practicum.stats.server.hit.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.stats.dto.StatsDto;
import ru.practicum.stats.server.hit.storage.HitStorage;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.stats.server.hit.mapper.HitMapper.toEndpointHit;
import static ru.practicum.stats.server.hit.mapper.HitMapper.toEndpointHitDto;

@Slf4j
@Service
@AllArgsConstructor
public class HitServiceImpl implements HitService {
    private final HitStorage hitStorage;

    @Override
    public HitDto createEndpointHit(HitDto endpointHitDto) {
        return toEndpointHitDto(hitStorage.save(toEndpointHit(endpointHitDto)));
    }

    @Override
    public List<StatsDto> getEndpointHits(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        return null;
    }
}
