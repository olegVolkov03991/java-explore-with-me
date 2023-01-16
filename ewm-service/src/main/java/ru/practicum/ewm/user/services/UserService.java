package ru.practicum.ewm.user.services;

import ru.practicum.ewm.user.model.dto.UserInputDto;
import ru.practicum.ewm.user.model.dto.UserOutputDto;

import java.util.List;

public interface UserService {
    List<UserOutputDto> getUsers(List<Long> ids, Integer from, Integer size);

    UserOutputDto create(UserInputDto userInputDto);

    void userBan(Long userId);

    void userUnban(Long userId);

    void remove(long id);
}
