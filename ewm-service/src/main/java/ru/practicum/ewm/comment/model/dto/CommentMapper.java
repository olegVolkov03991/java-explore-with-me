package ru.practicum.ewm.comment.model.dto;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.comment.model.Comment;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.user.model.User;

import java.util.Date;

@Component
public class CommentMapper {

    static Date date = new Date();

    public static Comment toComment(User initiator, String text, Event event) {
        return Comment.builder()
                .createdOn(date)
                .initiator(initiator)
                .text(text)
                .event(event)
                .build();
    }

    public static CommentOutputDto toCommentOutputDto(Comment comment) {
        return CommentOutputDto.builder()
                .id(comment.getId())
                .createdOn(comment.getCreatedOn())
                .initiator(comment.getInitiator())
                .text(comment.getText())
                .build();
    }
}
