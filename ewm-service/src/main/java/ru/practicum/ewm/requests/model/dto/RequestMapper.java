package ru.practicum.ewm.requests.model.dto;

import ru.practicum.ewm.requests.model.Request;

public class RequestMapper {


    public static RequestOutputDto toRequestDto(Request request) {
        return RequestOutputDto.builder()
                .id(request.getId())
                .requester(request.getRequester().getId())
                .event(request.getEvent().getId())
                .status(request.getStatus())
                .created(request.getCreated())
                .build();
    }

    public static RequestAllOutputDto requestAllOutputDto(Request request, Long userId, Long eventId) {
        return RequestAllOutputDto.builder()
                .id(request.getId())
                .requester(userId)
                .event(eventId)
                .status(request.getStatus())
                .created(request.getCreated())

                .build();
    }
}
