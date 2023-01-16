package ru.practicum.ewm.comment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;
import ru.practicum.ewm.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentPublicController {

    private final CommentService commentsService;

    @GetMapping()
    public List<CommentOutputDto> getAllComments(@Valid @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                 @Valid @RequestParam(defaultValue = "10") @Positive Integer size) {
        return commentsService.getAllComments(from, size);
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
