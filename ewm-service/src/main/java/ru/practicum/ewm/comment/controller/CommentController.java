package ru.practicum.ewm.comment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;
import ru.practicum.ewm.comment.service.CommentServiceImpl;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/comments")
public class CommentController {

    private final CommentServiceImpl commentsService;

    @PostMapping
    public CommentOutputDto createComment(@Valid @RequestBody String text,
                                          @Valid @PathVariable Long eventId,
                                          @Valid @PathVariable Long userId) throws Exception {
        return commentsService.createComment(text, eventId, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@Valid @PathVariable Long id) {
        commentsService.deleteComment(id);
    }

}
