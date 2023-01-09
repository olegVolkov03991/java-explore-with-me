package ru.practicum.ewm.events.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.locations.model.dto.LocationDto;
import ru.practicum.ewm.user.model.dto.UserOutputDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
public class EventOutputDto {

    private Long id;

    @NonNull
    private UserOutputDto initiator;

    @NotBlank
    private String annotation;

    @NonNull
    private Category category;

    private Long confirmedRequests;

    @Future
    private Date eventDate;

    private Date createdOn;

    private Date publishedOn;

    private String description;

    @NonNull
    private Boolean paid;

    private Long participantLimit;

    private Boolean requestModeration;

    @NonNull
    private String title;

    private State state;

    private Long views;

    private LocationDto location;
}
