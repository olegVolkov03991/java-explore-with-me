package ru.practicum.ewm.requests.services;

import ru.practicum.ewm.requests.model.dto.RequestOutputDto;

import java.util.List;

public interface RequestService {

    List<RequestOutputDto> getInformationAboutUserApplicationsForParticipationInOtherEvents(Long userId);

    RequestOutputDto confirmationApplicationByUserEvent(Long userId, Long eventId, Long reqId);

    RequestOutputDto rejectionApplicationByUserEvent(Long userId, Long eventId, Long reqId);

    List<RequestOutputDto> getRequestByInitiator(Long userId, Long eventId);

    RequestOutputDto cancelYourEventRequest(Long userId, Long requestId);

    RequestOutputDto create(long requestorId, Long eventId);
}
