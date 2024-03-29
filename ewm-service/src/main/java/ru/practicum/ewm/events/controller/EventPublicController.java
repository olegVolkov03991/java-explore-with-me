package ru.practicum.ewm.events.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.model.dto.EventOutputDto;
import ru.practicum.ewm.events.services.EventServiceImpl;
import ru.practicum.ewm.statistics.aop.CreatingHit;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventPublicController {

    private final EventServiceImpl eventService;


    @GetMapping("/{id}")
    @CreatingHit
    public EventOutputDto getPublishedEventById(HttpServletRequest request,
                                                @PathVariable Long id) {
        return eventService.getPublishedEventById(id);
    }

    @GetMapping()
    @CreatingHit
    public List<EventOutputDto> getEventsByLoc(HttpServletRequest request,
                                               @Valid @RequestParam(required = false) String text,
                                               @Valid @RequestParam(required = false) List<Long> categories,
                                               @Valid @RequestParam(required = false) Boolean paid,
                                               @Valid @RequestParam(required = false) String rangeStart,
                                               @Valid @RequestParam(required = false) String rangeEnd,
                                               @Valid @RequestParam(required = false) Boolean onlyAvailable,
                                               @Valid @RequestParam(required = false) String sort,
                                               @Valid @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                               @Valid @RequestParam(defaultValue = "10") @Positive Integer size) {
        return eventService.searchEventsByUser(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/loc")
    @CreatingHit
    public List<EventOutputDto> getEventsByLoc(HttpServletRequest request,
                                                    @RequestParam Double lat,
                                                    @RequestParam Double lon,
                                                    @RequestParam Double distance) {
        return eventService.getEventsByLoc(lat, lon, distance);
    }
}
