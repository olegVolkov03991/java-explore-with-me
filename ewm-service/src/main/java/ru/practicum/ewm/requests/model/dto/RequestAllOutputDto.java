package ru.practicum.ewm.requests.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.requests.model.Status;

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
