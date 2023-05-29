package ru.practicum.hit.entity;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    private String uri;
    private String app;
    private long hits;
}