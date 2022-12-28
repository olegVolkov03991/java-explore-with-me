package ru.practicum.ewm.requests.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.requests.model.dto.RequestOutputDto;
import ru.practicum.ewm.requests.services.RequestServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users/{userId}/events")
public class RequestAdminController {

    private final RequestServiceImpl requestService;


    @GetMapping("/{eventId}/requests")
    public List<RequestOutputDto> getRequestByInitiator(@PathVariable Long userId,
                                                        @PathVariable Long eventId) {
        return requestService.getRequestByInitiator(userId, eventId);
    }


    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public RequestOutputDto RejectionApplicationByUserEvent(@PathVariable Long userId,
                                                            @PathVariable Long eventId,
                                                            @PathVariable Long reqId) {
        return requestService.RejectionApplicationByUserEvent(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public RequestOutputDto ConfirmationApplicationByUserEvent(@PathVariable Long userId,
                                                               @PathVariable Long eventId,
                                                               @PathVariable Long reqId) {
        return requestService.ConfirmationApplicationByUserEvent(userId, eventId, reqId);
    }
}
