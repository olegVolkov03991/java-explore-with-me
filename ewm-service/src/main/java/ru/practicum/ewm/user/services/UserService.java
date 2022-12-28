package ru.practicum.ewm.user.services;

import ru.practicum.ewm.user.model.dto.UserInputDto;
import ru.practicum.ewm.user.model.dto.UserOutputDto;

import java.util.List;

public interface UserService {
    UserOutputDto create(UserInputDto userInputDto);

    void remove(long id);

    List<UserOutputDto> getUsers(List<Long> ids, Integer from, Integer size);
}
