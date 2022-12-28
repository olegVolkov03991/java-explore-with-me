package ru.practicum.ewm.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.events.model.dto.EventInputDto;
import ru.practicum.ewm.events.model.dto.EventOutputDto;
import ru.practicum.ewm.events.services.EventServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")

public class AdminEventsController {

    private final EventServiceImpl eventService;

    /*
    получение всех событий по заданным параметрам
    * */
    @GetMapping
    public List<EventOutputDto> getAllEventsByParameters(@Valid @RequestParam(required = false) List<Long> users,
                                                         @Valid @RequestParam(required = false) List<State> states,
                                                         @Valid @RequestParam(required = false) List<Long> categories,
                                                         @Valid @RequestParam(required = false) String rangeStart,
                                                         @Valid @RequestParam(required = false) String rangeEnd,
                                                         @Valid @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                         @Valid @RequestParam(defaultValue = "10") @Positive Integer size) {
        return eventService.getAllEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }


    @PatchMapping("/{eventId}/publish")
    public EventOutputDto publishingEvent(@Valid @PathVariable long eventId) {
        return eventService.publishingEvent(eventId);
    }

    /*rejected ad event*/
    @PatchMapping("/{eventId}/reject")
    public EventOutputDto rejectionEvent(@Valid @PathVariable long eventId) {
        return eventService.rejectionEvent(eventId);
    }


    /*
    редактирование события администратором
    * */
    @PutMapping("/{eventId}")
    public EventOutputDto editingEventByAdmin(@PathVariable long eventId,
                                              @RequestBody EventInputDto inputEvent) {
        return eventService.editingEventByAdmin(eventId, inputEvent);
    }


}
