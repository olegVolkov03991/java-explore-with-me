package ru.practicum.ewm.comment;

import ru.practicum.ewm.comment.model.dto.CommentOutputDto;

import java.util.List;

public interface CommentService {

    CommentOutputDto createComment(String text, Long eventId, Long userId) throws Exception;

    void deleteComment(Long id);

    List<CommentOutputDto> getAllComments();

    CommentOutputDto getCommentById(Long commentId);

    CommentOutputDto updateComment(String updateText, Long commentId);


}
