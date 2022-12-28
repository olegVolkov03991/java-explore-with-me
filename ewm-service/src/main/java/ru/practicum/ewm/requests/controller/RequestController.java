package ru.practicum.ewm.requests.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.requests.model.dto.RequestAllOutputDto;
import ru.practicum.ewm.requests.model.dto.RequestOutputDto;
import ru.practicum.ewm.requests.services.RequestServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
public class RequestController {
    private final RequestServiceImpl requestService;

    @PostMapping
    public RequestOutputDto create(@Valid @PathVariable Long userId,
                                   @Valid @RequestParam Long eventId) {
        return requestService.create(userId, eventId);
    }


    @PatchMapping("/{requestId}/cancel")
    public RequestAllOutputDto CancelYourEventRequest(@Valid @PathVariable Long userId, @Valid @PathVariable Long requestId) {
        return requestService.CancelYourEventRequest(userId, requestId);
    }


    /*
    получение информации о заявках пользователей на участие в других мероприятиях
    */

    @GetMapping
    public List<RequestOutputDto> getInformationAboutUserApplicationsForParticipationInOtherEvents(@Valid @PathVariable Long userId) {
        return requestService.getInformationAboutUserApplicationsForParticipationInOtherEvents(userId);
    }


}

