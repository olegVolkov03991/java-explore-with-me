package ru.practicum.ewm.comment.model.dto;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.comment.model.Comment;

import java.util.Date;

@Component
public class CommentMapper {

    static Date date = new Date();

    public static Comment toComment(CommentInputDto commentInputDto){
        return Comment.builder()
                .createdOn(date)
                .initiator(commentInputDto.getInitiator())
                .text(commentInputDto.getText())
                .build();
    }

    public static CommentOutputDto toCommentOutputDto(Comment comment){
        return CommentOutputDto.builder()
                .createdOn(comment.getCreatedOn())
                .initiator(comment.getInitiator())
                .text(comment.getText())
                .build();
    }
}
