package ru.practicum.ewm.user.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserInputDto {
    @NotNull
    private String name;
    @NotNull
    private String email;
}
