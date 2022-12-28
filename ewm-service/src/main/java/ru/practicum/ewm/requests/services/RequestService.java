package ru.practicum.ewm.requests.services;

import ru.practicum.ewm.requests.model.dto.RequestAllOutputDto;
import ru.practicum.ewm.requests.model.dto.RequestOutputDto;

import java.util.List;

public interface RequestService {
    // RequestOutputDto create(long requestorId, long eventId) ;

    RequestOutputDto create(long requestorId, Long eventId);

    List<RequestOutputDto> getRequestByInitiator(Long userId,
                                                 Long eventId);

    RequestAllOutputDto CancelYourEventRequest(Long userId, Long requestId);

    RequestOutputDto RejectionApplicationByUserEvent(Long userId, Long eventId, Long reqId);
    RequestOutputDto ConfirmationApplicationByUserEvent(Long userId, Long eventId, Long reqId);
    List<RequestOutputDto> getInformationAboutUserApplicationsForParticipationInOtherEvents(Long userId);
}
