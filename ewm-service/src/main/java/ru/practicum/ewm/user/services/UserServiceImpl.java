package ru.practicum.ewm.user.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.model.dto.UserInputDto;
import ru.practicum.ewm.user.model.dto.UserMapper;
import ru.practicum.ewm.user.model.dto.UserOutputDto;
import ru.practicum.ewm.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    @Override
    @Transactional
    public UserOutputDto create(UserInputDto userInputDto) {
        if (userRepository.existsByName(userInputDto.getName())) {
            throw new ConflictException("this name already exists");
        }
        log.info("<create USER> create user: name - {} ", userInputDto.getName());

        return UserMapper.userOutputDto(
                userRepository.save(
                        UserMapper.toUser(userInputDto)));
    }



    @Override
    @Transactional
    public void remove(long id) {
        userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        userRepository.deleteById(id);
    }



    @Override
    public List<UserOutputDto> getUsers(List<Long> ids, Integer from, Integer size) {
        Page<User> users;
        if (ids == null || ids.isEmpty()) {
            users = userRepository.findAll(getPageRequest(from, size));
        } else {
            users = userRepository.getUsersByIdIsIn(ids, getPageRequest(from, size));
            if (users.isEmpty()) {
            }
        }
        return users.stream()
                .map(UserMapper::userOutputDto)
                .collect(Collectors.toList());
    }

    private PageRequest getPageRequest(Integer from, Integer size) {
        int page = from < size ? 0 : from / size;
        return PageRequest.of(page, size);
    }
}
