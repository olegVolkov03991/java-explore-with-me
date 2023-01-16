package ru.practicum.ewm.comment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;
import ru.practicum.ewm.comment.service.CommentServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentPublicController {

    private final CommentServiceImpl commentsService;

    @GetMapping()
    public List<CommentOutputDto> getAllComments() {
        return commentsService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public CommentOutputDto getCommentById(@Valid @PathVariable Long commentId) {
        return commentsService.getCommentById(commentId);
    }

    @PatchMapping("/{commentId}")
    public CommentOutputDto getCommentById(@Valid @PathVariable Long commentId, @Valid @RequestBody String text) {
        return commentsService.updateComment(text, commentId);
    }

}
