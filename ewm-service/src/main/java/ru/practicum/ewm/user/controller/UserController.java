package ru.practicum.ewm.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.user.model.dto.UserInputDto;
import ru.practicum.ewm.user.model.dto.UserOutputDto;
import ru.practicum.ewm.user.services.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public UserOutputDto create(@Valid @RequestBody UserInputDto userInputDto) {
        return userService.create(userInputDto);
    }

    @DeleteMapping("/{userId}")
    public void remove(@Valid @PathVariable Long userId) {
        userService.remove(userId);
    }

    @GetMapping
    public List<UserOutputDto> getByIds(@Valid @RequestParam(required = false) List<Long> ids,
                                        @Valid @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                        @Valid @RequestParam(defaultValue = "10") @Positive Integer size) {
        return userService.getUsers(ids, from, size);
    }
}
