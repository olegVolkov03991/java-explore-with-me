package ru.practicum.ewm.user.model.dto;

import ru.practicum.ewm.user.model.User;

public class UserMapper {
    public static User toUser(UserInputDto userInputDto) {
        return User.builder()
                .name(userInputDto.getName())
                .email(userInputDto.getEmail())
                .build();
    }


    public static UserInputDto toUserInputDto(User user) {
       return UserInputDto.builder()
               .name(user.getName())
               .email(user.getEmail())
               .build();
    }

    public static UserOutputDto userOutputDto(User user){
        return UserOutputDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
