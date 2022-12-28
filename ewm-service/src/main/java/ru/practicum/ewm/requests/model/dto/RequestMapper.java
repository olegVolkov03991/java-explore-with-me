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

    public static RequestAllOutputDto requestAllOutputDto(Request request) {
        return RequestAllOutputDto.builder()
                .id(request.getId())
                .requester(request.getRequester().getId())
                .event(request.getEvent().getId())
                .status(request.getStatus())
                .created(request.getCreated())

                .build();
    }
}
