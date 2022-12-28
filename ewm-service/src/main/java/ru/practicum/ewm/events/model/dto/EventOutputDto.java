package ru.practicum.ewm.events.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.locations.model.dto.LocationDto;
import ru.practicum.ewm.user.model.dto.UserOutputDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    private LocalDateTime eventDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

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
