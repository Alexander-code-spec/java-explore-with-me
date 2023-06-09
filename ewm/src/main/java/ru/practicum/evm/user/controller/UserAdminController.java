package ru.practicum.evm.user.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.evm.user.service.UserService;
import ru.practicum.evm.user.dto.UserDto;
import lombok.AllArgsConstructor;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class UserAdminController {
    private final UserService userService;

    @ResponseStatus(CREATED)
    @PostMapping("/users")
    public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  @RequestParam(defaultValue = "0") Integer from) {
        return userService.getUsers(ids, from, size);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
