package ru.practicum.ewm.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentPublicController {

    private final CommentServiceImpl commentsService;

    @GetMapping()
    public List<CommentOutputDto> getAllComments(){
        return commentsService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public CommentOutputDto getCommentById(@PathVariable Long commentId){
        return commentsService.getCommentById(commentId);
    }

    @PatchMapping("/{commentId}")
    public CommentOutputDto getCommentById(@PathVariable Long commentId, @RequestBody String text){
        return commentsService.updateComment(text, commentId);
    }

}
