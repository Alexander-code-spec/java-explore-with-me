package ru.practicum.evm.user.service;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.evm.user.entity.User;
import ru.practicum.evm.user.exception.NameExistException;
import ru.practicum.evm.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import ru.practicum.evm.user.mapper.UserMapper;
import ru.practicum.evm.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.springframework.data.domain.PageRequest.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        if (userRepository.existsByName(userDto.getName()))
            throw new NameExistException("User with name " + userDto.getName() + " cannot be saved");
        User user = userMapper.toUser(userDto);
        User saved = userRepository.save(user);
        return userMapper.toUserDto(saved);
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids,
                                  Integer from,
                                  Integer size) {
        if (ids == null) {
            return userMapper.toUserDtos(userRepository.findAll(of(from / size, size)).toList());
        }
        return ids.isEmpty()
                ? userMapper.toUserDtos(userRepository.findAll(of(from / size, size)).toList())
                : userMapper.toUserDtos(userRepository.findAllById(ids));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
