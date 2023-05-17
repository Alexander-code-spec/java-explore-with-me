package ru.practicum.stats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HitDto {
    Long id;
    String ip;
    String app;
    String uri;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
}