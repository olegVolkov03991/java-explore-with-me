package ru.practicum.ewm.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.comment.model.Comment;
import ru.practicum.ewm.comment.model.dto.CommentMapper;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.repository.EventRepository;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public CommentOutputDto createComment(String text, Long eventId, Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(ObjectNotFoundException::new);
        if(user.getBanUser()){
            throw new Exception("\n" +
                    "This user has been banned and cannot post comments.");
        }
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        Comment comment = CommentMapper.toComment(user, text, event);
        commentRepository.save(comment);
        log.info("create {}", comment.getText());
        return CommentMapper.toCommentOutputDto(comment);
    }

    @Override
    public void deleteComment(Long id) {
        userRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        commentRepository.deleteById(id);
        log.info("comment delete");
    }

    @Override
    public List<CommentOutputDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(this::getCommentOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentOutputDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(ObjectNotFoundException::new);
        log.info("get {}", comment.getText());
        return CommentMapper.toCommentOutputDto(comment);
    }

    @Override
    public CommentOutputDto updateComment(String updateText, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(ObjectNotFoundException::new);
        comment.setText(updateText);
        commentRepository.save(comment);
        log.info("update {}", comment.getText());
        return CommentMapper.toCommentOutputDto(comment);
    }


    public CommentOutputDto getCommentOutputDto(Comment comment) {
        return CommentMapper.toCommentOutputDto(comment);
    }
}
