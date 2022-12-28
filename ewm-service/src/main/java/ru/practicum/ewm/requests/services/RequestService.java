package ru.practicum.ewm.requests.services;

import ru.practicum.ewm.requests.model.dto.RequestOutputDto;

import java.util.List;

public interface RequestService {

    List<RequestOutputDto> getInformationAboutUserApplicationsForParticipationInOtherEvents(Long userId);
    RequestOutputDto ConfirmationApplicationByUserEvent(Long userId, Long eventId, Long reqId);
    RequestOutputDto RejectionApplicationByUserEvent(Long userId, Long eventId, Long reqId);
    List<RequestOutputDto> getRequestByInitiator(Long userId, Long eventId);
    RequestOutputDto CancelYourEventRequest(Long userId, Long requestId);
    RequestOutputDto create(long requestorId, Long eventId);
}
