package ru.practicum.ewm.comment.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.user.model.User;

import java.util.Date;

@Data
@Builder
public class CommentOutputDto {

    private String text;

    private User initiator;

    private Date createdOn;
}
