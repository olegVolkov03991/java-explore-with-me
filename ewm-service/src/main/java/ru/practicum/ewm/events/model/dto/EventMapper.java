package ru.practicum.ewm.events.model.dto;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.locations.model.dto.LocationMapper;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.model.dto.UserMapper;

import java.util.Date;

@Component
public class EventMapper {

    static Date date = new Date();

    public static Event toEvent(EventInputDto eventInputDto,
                                Category category,
                                User initiator,
                                Location location) {
        return Event.builder()
                .annotation(eventInputDto.getAnnotation())
                .category(category)
                .description(eventInputDto.getDescription())
                .eventDate(eventInputDto.getEventDate())
                .initiator(initiator)
                .paid(eventInputDto.getPaid())
                .participantLimit(eventInputDto.getParticipantLimit())
                .title(eventInputDto.getTitle())
                .publishedOn(date)
                .createdOn(date)
                .requestModeration(eventInputDto.getRequestModeration())
                .state(State.PENDING)
                .location(location)
                .build();
    }


    public static EventOutputDto eventOutputDto(Event event, long request, long views) {
        return EventOutputDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(event.getCategory())
                .confirmedRequests(request)
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(UserMapper.userOutputDto(event.getInitiator()))
                .location(LocationMapper.toLocationDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(views)
                .build();
    }

    public static EventInputDto toEventInputDtoFromUpdate(EventUpdateDto update, Event oldEvent) {
        return EventInputDto.builder()
                .annotation(update.getAnnotation() != null ? update.getAnnotation() : oldEvent.getAnnotation())
                .category(update.getCategory() != null ? update.getCategory() : oldEvent.getCategory().getId())
                .description(update.getDescription() != null ? update.getDescription() : oldEvent.getDescription())
                .eventDate(update.getEventDate() != null ? update.getEventDate() : oldEvent.getEventDate())
                .location(update.getLocation() != null ? LocationMapper.toLocation(update.getLocation()) : oldEvent.getLocation())
                .paid(update.getPaid() != null ? update.getPaid() : oldEvent.getPaid())
                .participantLimit(update.getParticipantLimit() != null ? update.getParticipantLimit() : oldEvent.getParticipantLimit())
                .title(update.getTitle() != null ? update.getTitle() : oldEvent.getTitle())
                .requestModeration(update.getRequestModeration() != null ? update.getRequestModeration() : oldEvent.getRequestModeration())
                .build();
    }


}
