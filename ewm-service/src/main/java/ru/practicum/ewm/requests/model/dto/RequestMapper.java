package ru.practicum.ewm.requests.model.dto;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.requests.model.Request;

@Component
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
}
