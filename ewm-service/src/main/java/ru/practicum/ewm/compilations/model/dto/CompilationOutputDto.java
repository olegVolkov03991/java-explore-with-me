package ru.practicum.ewm.compilations.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.ewm.events.model.Event;

import java.util.Set;

@Data
@Builder
public class CompilationOutputDto {
    private long id;
    private String title;
    private Boolean pinned;
    private Set<Event> events;


}
