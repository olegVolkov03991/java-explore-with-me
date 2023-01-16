package ru.practicum.ewm.requests.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.requests.model.dto.RequestOutputDto;
import ru.practicum.ewm.requests.services.RequestService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class RequestAdminController {

    private final RequestService requestService;


    @GetMapping("/{eventId}/requests")
    public List<RequestOutputDto> getRequestByInitiator(@Valid @PathVariable Long userId,
                                                        @Valid @PathVariable Long eventId) {
        return requestService.getRequestByInitiator(userId, eventId);
    }


    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public RequestOutputDto rejectionApplicationByUserEvent(@Valid @PathVariable Long userId,
                                                            @Valid @PathVariable Long eventId,
                                                            @Valid @PathVariable Long reqId) {
        return requestService.rejectionApplicationByUserEvent(userId, eventId, reqId);
    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public RequestOutputDto confirmationApplicationByUserEvent(@Valid @PathVariable Long userId,
                                                               @Valid @PathVariable Long eventId,
                                                               @Valid @PathVariable Long reqId) {
        return requestService.confirmationApplicationByUserEvent(userId, eventId, reqId);
    }
}
