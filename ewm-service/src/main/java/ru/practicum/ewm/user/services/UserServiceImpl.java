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


    /*
    create user
    */
    @Override
    @Transactional
    public UserOutputDto create(UserInputDto userInputDto) {
        if (userRepository.findByName(userInputDto.getName()) != null) {
            throw new ConflictException("this name already exists");
        }
        log.info("<create USER> create user: name - {} ", userInputDto.getName());

        return UserMapper.userOutputDto(
                userRepository.save(
                        UserMapper.toUser(userInputDto)));
    }


    /*
    delete user by UserId
    */
    @Override
    @Transactional
    public void remove(long id) {
        log.info("<remove> delete user {} ", id);
        userRepository.deleteById(id);
    }

    /*
    get users by list id
    */
    @Override
    public List<UserOutputDto> getUsers(List<Long> ids, Integer from, Integer size) {
        Page<User> users;
        if (ids == null || ids.isEmpty()) {
            users = userRepository.findAll(getPageRequest(from, size));
            log.info("<getUsers> get all users {} ", users);
        }
        else if (ids.size() == 1) {
            User user = userRepository.findById(ids.get(0))
                    .orElseThrow(ObjectNotFoundException::new);
            log.info("<getUser> get user: name - {} ", user.getName());
            return List.of(UserMapper.userOutputDto(user));
        }
        else {
            users = userRepository.getUsersByIdIsInOrderByIdAsc(ids, getPageRequest(from, size));
            if (users.isEmpty()) {
                log.warn("page users is empty");
                throw new ObjectNotFoundException();
            }
            users.stream()
                    .forEach(user -> ids
                            .remove(user.getId()));
            if (!ids.isEmpty()) {
                log.warn("Users with id={} were not found", ids);
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
