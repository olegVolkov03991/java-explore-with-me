package ru.practicum.ewm.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.model.Comment;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/comments")
public class CommentController {

    private final CommentServiceImpl commentsService;

    @PostMapping
    public CommentOutputDto createComment(@RequestBody String text,
                                          @PathVariable Long eventId,
                                          @PathVariable Long userId) throws Exception {
        return commentsService.createComment(text,eventId, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentsService.deleteComment(id);
    }

}
