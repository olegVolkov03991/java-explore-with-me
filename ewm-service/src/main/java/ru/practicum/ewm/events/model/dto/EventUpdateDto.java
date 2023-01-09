package ru.practicum.ewm.events.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.locations.model.dto.LocationDto;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class EventUpdateDto {

    @NotNull
    private Long eventId;

    private String annotation;

    private Long category;

    private Date eventDate;

    private String description;

    private LocationDto location;

    private Boolean paid;

    private Long participantLimit;

    private Boolean requestModeration;

    private String title;
}
