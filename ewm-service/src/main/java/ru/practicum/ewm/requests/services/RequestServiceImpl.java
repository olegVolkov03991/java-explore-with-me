package ru.practicum.ewm.requests.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.repository.EventRepository;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;
import ru.practicum.ewm.requests.model.Request;
import ru.practicum.ewm.requests.model.Status;
import ru.practicum.ewm.requests.model.dto.RequestMapper;
import ru.practicum.ewm.requests.model.dto.RequestOutputDto;
import ru.practicum.ewm.requests.repository.RequestRepository;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public RequestOutputDto create(long requestorId, Long eventId) {
        if (eventId == 0) {
            eventId = 1L;
        }
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        if (event.getInitiator().getId().equals(requestorId)) {
            log.error("this user is initiator this event");
        }
        User user = userRepository.findById(requestorId).orElseThrow(ObjectNotFoundException::new);
        Request request = Request.builder()
                .requester(user)
                .created(LocalDateTime.now())
                .status(Status.PENDING)
                .event(event)
                .build();
        requestRepository.save(request);
        event.setId(eventId);
        RequestOutputDto requestDto = RequestMapper.toRequestDto(request);
        log.info("create request {} ", requestDto);
        return requestDto;
    }

    @Override
    public List<RequestOutputDto> getRequestByInitiator(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(ObjectNotFoundException::new);
        return requestRepository.findRequestByEventId(eventId)
                .stream()
                .map(RequestMapper::toRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public RequestOutputDto CancelYourEventRequest(Long userId, Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(ObjectNotFoundException::new);
        request.setStatus(Status.CANCELED);
        RequestOutputDto requestOutputDto = RequestMapper.toRequestDto(requestRepository.save(request));
        return requestOutputDto;
    }

    @Override
    public RequestOutputDto RejectionApplicationByUserEvent(Long userId, Long eventId, Long reqId) {
        Request request = requestRepository.findById(reqId)
                .orElseThrow(ObjectNotFoundException::new);

        request.setStatus(Status.REJECTED);

        return  RequestMapper.toRequestDto(requestRepository.save(request));
    }

    @Override
    public RequestOutputDto ConfirmationApplicationByUserEvent(Long userId, Long eventId, Long reqId) {
        Request request = requestRepository.findById(reqId)
                .orElseThrow(ObjectNotFoundException::new);

        request.setStatus(Status.CONFIRMED);

        return  RequestMapper.toRequestDto(requestRepository.save(request));
    }

    @Override
    public List<RequestOutputDto> getInformationAboutUserApplicationsForParticipationInOtherEvents(Long userId) {
       List<Request> requests = requestRepository.getRequestByRequester(userId);
       log.info("get request bu user id {} " , requests);

       return requests.stream()
               .map(RequestMapper::toRequestDto)
               .collect(Collectors.toList());


    }
}
