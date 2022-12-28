package ru.practicum.ewm.events.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.model.dto.*;
import ru.practicum.ewm.events.services.EventServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users/{userId}/events")
public class EventController {

    private final EventServiceImpl eventService;

    @PostMapping()
    public EventOutputShortDto createEvent(@PathVariable Long userId,
                                           @RequestBody @Valid EventInputDto eventInputDto) {
        return eventService.create(userId, eventInputDto);
    }

    @PatchMapping
    public EventOutputDto updateByInitiator(@PathVariable Long userId,
                                            @RequestBody @Valid EventUpdateDto eventUpdateDto) {
        return eventService.updateEventByUser(userId, eventUpdateDto);
    }

    @PatchMapping("/{eventId}")
    public EventOutputDto rejectEventByInitiator(@PathVariable Long userId,
                                                 @PathVariable Long eventId) {
        return eventService.rejectEventByInitiator(userId, eventId);
    }

    @GetMapping
    public List<EventOutputShortDto> getAllEventsByUserId(@PathVariable Long userId,
                                                          @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                          @RequestParam(defaultValue = "10") @Positive Integer size) {
        return eventService.getAllEventsByUserId(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByInitiator(@PathVariable Long userId,
                                            @PathVariable Long eventId) {
        return eventService.getEventByInitiator(userId, eventId);
    }

}
