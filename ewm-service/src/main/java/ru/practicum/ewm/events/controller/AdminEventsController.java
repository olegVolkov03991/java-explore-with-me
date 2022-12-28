package ru.practicum.ewm.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.model.dto.EventInputDto;
import ru.practicum.ewm.events.model.dto.EventOutputShortDto;
import ru.practicum.ewm.events.services.EventServiceImpl;
import ru.practicum.ewm.events.model.State;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/events")

public class AdminEventsController {

    private final EventServiceImpl eventService;

    /*
    получение всех событий по заданным параметрам
    * */
    @GetMapping
    public List<EventOutputShortDto> getAllEventsByParameters(@RequestParam(required = false) List<Long> users,
                                                              @RequestParam(required = false) List<State> states,
                                                              @RequestParam(required = false) List<Long> categories,
                                                              @RequestParam(required = false) String rangeStart,
                                                              @RequestParam(required = false) String rangeEnd,
                                                              @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                              @RequestParam(defaultValue = "10") @Positive Integer size) {
        return eventService.getAllEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }


    @PatchMapping("/{eventId}/publish")
    public EventOutputShortDto publishingEvent(@PathVariable long eventId) {
        return eventService.publishingEvent(eventId);
    }

    /*rejected ad event*/
    @PatchMapping("/{eventId}/reject")
    public EventOutputShortDto rejectionEvent(@PathVariable long eventId) {
        return eventService.rejectionEvent(eventId);
    }


    /*
    редактирование события администратором
    * */
    @PutMapping("/{eventId}")
    public EventOutputShortDto editingEventByAdmin(@PathVariable long eventId,
                                                   @RequestBody EventInputDto inputEvent) {
        return eventService.editingEventByAdmin(eventId, inputEvent);
    }


}
