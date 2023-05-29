package ru.practicum.hit.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.hit.entity.Hit;

public interface StatsStorage extends JpaRepository<Hit, Long>, HitStorage {
}
