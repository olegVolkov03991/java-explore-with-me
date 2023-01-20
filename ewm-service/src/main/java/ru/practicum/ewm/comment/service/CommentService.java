package ru.practicum.ewm.comment.service;

import ru.practicum.ewm.comment.model.dto.CommentOutputDto;

import java.util.List;

public interface CommentService {

    CommentOutputDto createComment(String text, Long eventId, Long userId) throws Exception;

    CommentOutputDto updateComment(String updateText, Long commentId);

    CommentOutputDto getCommentById(Long commentId);

    List<CommentOutputDto> getAllComments(Integer from, Integer size);

    void deleteComment(Long id);
}
