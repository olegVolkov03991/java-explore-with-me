package ru.practicum.ewm.events.services;

import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.ewm.events.model.*;
import ru.practicum.ewm.events.model.dto.*;

import java.util.List;

public interface EventService {
    EventOutputShortDto create(long userId, EventInputDto eventDto);

    EventOutputShortDto publishingEvent(long eventId);

    EventOutputShortDto rejectionEvent(long eventId);

    EventOutputShortDto editingEventByAdmin(long eventId, EventInputDto eventInputDto);

    EventFullDto getPublishedEventById(long id);

    EventOutputDto updateEventByUser(long userId, EventUpdateDto eventUpdateDto);

    EventFullDto getEventByInitiator( Long userId, Long eventId);


    List<EventOutputShortDto> getAllEventsByUserId(long id,
                                                   Integer from,
                                                   Integer size);



    List<EventOutputShortDto> getAllEvents(List<Long> users, List<State> states,
                                           List<Long> categories,
                                           String rangeStart,
                                           String rangeEnd,
                                           Integer from,
                                           Integer size);


    EventOutputDto rejectEventByInitiator(@PathVariable Long userId,
                                          @PathVariable Long eventId);


    public List<EventOutputShortDto> searchEventsByUser(String text,
                                                        List<Long> categories,
                                                        Boolean paid,
                                                        String rangeStart,
                                                        String rangeEnd,
                                                        Boolean onlyAvailable,
                                                        String sort,
                                                        Integer from,
                                                        Integer size);


}
