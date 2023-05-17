package ru.practicum.stats.server.hit.model;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Getter
@RequiredArgsConstructor
public class Stats {
    private final String uri;
    private final String app;
    private final Long hits;
}