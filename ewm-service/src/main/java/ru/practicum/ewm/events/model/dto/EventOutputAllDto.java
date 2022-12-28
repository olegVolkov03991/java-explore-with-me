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
public class EventOutputAllDto {
    private Long id;

    @NonNull
    private String title;

    @NotBlank
    private String annotation;

    @NonNull
    private Category category;

    @NonNull
    private Boolean paid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    private LocalDateTime eventDate;

    @NonNull
    private UserOutputDto initiator;

    private Long confirmedRequests;

    private String description;

    private Long participantLimit;//

    private State state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;//

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;//

    private LocationDto location;//

    private Boolean requestModeration;//
}
