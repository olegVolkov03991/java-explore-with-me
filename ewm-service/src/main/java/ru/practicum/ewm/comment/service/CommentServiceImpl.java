package ru.practicum.ewm.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.comment.model.Comment;
import ru.practicum.ewm.comment.model.dto.CommentMapper;
import ru.practicum.ewm.comment.model.dto.CommentOutputDto;
import ru.practicum.ewm.comment.repository.CommentRepository;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.repository.EventRepository;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

import javax.transaction.Transactional;
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
    @Transactional
    public CommentOutputDto createComment(String text, Long eventId, Long userId) throws Exception {
        User user = findUserById(userId);
        if (user.getBanUser()) {
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
    @Transactional
    public void deleteComment(Long id) {
        findCommentById(id);
        commentRepository.deleteById(id);
        log.info("comment delete");
    }

    @Override
    public List<CommentOutputDto> getAllComments(Integer from, Integer size) {
        Page<Comment> comments;
        comments = commentRepository.findAll(getPageRequest(from, size));
        return comments.stream()
                .map(this::getCommentOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentOutputDto getCommentById(Long commentId) {
        Comment comment = findCommentById(commentId);
        log.info("get {}", comment.getText());
        return CommentMapper.toCommentOutputDto(comment);
    }

    @Override
    @Transactional
    public CommentOutputDto updateComment(String updateText, Long commentId) {
        Comment comment = findCommentById(commentId);
        comment.setText(updateText);
        commentRepository.saveAndFlush(comment);
        log.info("update {}", comment.getText());
        return CommentMapper.toCommentOutputDto(comment);
    }


    private CommentOutputDto getCommentOutputDto(Comment comment) {
        return CommentMapper.toCommentOutputDto(comment);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(ObjectNotFoundException::new);
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(ObjectNotFoundException::new);
    }

    private PageRequest getPageRequest(Integer from, Integer size) {
        int page = from < size ? 0 : from / size;
        return PageRequest.of(page, size);
    }
}
