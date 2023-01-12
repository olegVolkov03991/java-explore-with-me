package ru.practicum.ewm.comment.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.user.model.User;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CommentInputDto {
    @NotNull
    private String text;

    private User initiator;

}
