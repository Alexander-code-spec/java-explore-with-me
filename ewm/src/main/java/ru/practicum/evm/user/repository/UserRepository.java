package ru.practicum.evm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.evm.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
        Boolean existsByName(String name);
}
