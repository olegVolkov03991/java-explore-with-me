package ru.practicum.ewm.requests.model.dto;

import lombok.Builder;
import lombok.Data;

import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.requests.model.Status;
import ru.practicum.ewm.user.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestAllOutputDto {

    Long id;
    Long requester;
    Long event;
    Status status;
    LocalDateTime created;
}
