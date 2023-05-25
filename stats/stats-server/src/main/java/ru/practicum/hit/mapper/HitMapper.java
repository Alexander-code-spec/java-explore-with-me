package ru.practicum.hit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.hit.utils.Patterns;
import ru.practicum.stats.dto.HitDto;
import ru.practicum.hit.entity.Hit;

@Mapper(componentModel = "spring")
public interface HitMapper {
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = Patterns.DATE_PATTERN)
    Hit toEntity(HitDto hitDto);
}