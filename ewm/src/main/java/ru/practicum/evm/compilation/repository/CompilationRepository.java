package ru.practicum.evm.compilation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.evm.compilation.entity.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

}