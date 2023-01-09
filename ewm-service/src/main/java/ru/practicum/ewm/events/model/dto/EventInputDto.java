package ru.practicum.ewm.events.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.user.model.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class EventInputDto {


    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String annotation;

    @NotNull
    private Long category;

    private Boolean paid;

    private Long participantLimit;

    private User initiator;

    private Location location;

    @Future
    private Date eventDate;

    private Date createdOn;

    private Date publishedOn;

    @Builder.Default
    private Boolean requestModeration = true;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.PENDING;
}
