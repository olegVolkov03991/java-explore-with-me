package ru.practicum.ewm.events.services;

import org.springframework.web.bind.annotation.PathVariable;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.events.model.dto.*;

import java.util.List;

public interface EventService {
    EventOutputDto create(long userId, EventInputDto eventDto);

    EventOutputDto publishingEvent(long eventId);

    EventOutputDto rejectionEvent(long eventId);

    EventOutputDto editingEventByAdmin(long eventId, EventInputDto eventInputDto);

    EventOutputDto getPublishedEventById(long id);

    EventOutputDto updateEventByUser(long userId, EventUpdateDto eventUpdateDto);

    EventOutputDto getEventByInitiator( Long userId, Long eventId);


    List<EventOutputDto> getAllEventsByUserId(long id,
                                                   Integer from,
                                                   Integer size);



    List<EventOutputDto> getAllEvents(List<Long> users, List<State> states,
                                           List<Long> categories,
                                           String rangeStart,
                                           String rangeEnd,
                                           Integer from,
                                           Integer size);


    EventOutputDto rejectEventByInitiator(@PathVariable Long userId,
                                          @PathVariable Long eventId);


    public List<EventOutputDto> searchEventsByUser(String text,
                                                        List<Long> categories,
                                                        Boolean paid,
                                                        String rangeStart,
                                                        String rangeEnd,
                                                        Boolean onlyAvailable,
                                                        String sort,
                                                        Integer from,
                                                        Integer size);


}
