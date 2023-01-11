package ru.practicum.ewm.events.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.categories.repository.CategoryRepository;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.events.model.dto.EventInputDto;
import ru.practicum.ewm.events.model.dto.EventMapper;
import ru.practicum.ewm.events.model.dto.EventOutputDto;
import ru.practicum.ewm.events.model.dto.EventUpdateDto;
import ru.practicum.ewm.events.repository.EventRepository;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.locations.repository.LocationRepository;
import ru.practicum.ewm.requests.repository.RequestRepository;
import ru.practicum.ewm.statistics.client.StatisticsClient;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RequestRepository requestRepository;
    private final LocationRepository locationRepository;
    private final StatisticsClient statisticsClient;


    @Override
    @Transactional
    public EventOutputDto create(long userId, EventInputDto eventInputDto) {
        User initiator = userRepository.findById(userId)
                .orElseThrow(ObjectNotFoundException::new);
        Category category = getCategory(eventInputDto.getCategory());
        Location location = getOrCreateLocation(eventInputDto);
        Event event = EventMapper.toEvent(eventInputDto, category, initiator, location);
        eventRepository.save(event);
        long views = statisticsClient.getStatsForEvent(event.getId());
        EventOutputDto eventOutputShortDto = EventMapper.eventOutputDto(event, 0L, views);
        log.info("<create EVENT> create {} ", eventOutputShortDto);
        return eventOutputShortDto;
    }

    @Override
    public EventOutputDto getPublishedEventById(Long id) {
        eventRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        Event event = eventRepository.getById(id);
        long requestId = requestRepository.getCountConfirmedRequestByEventId(id);
        long views = statisticsClient.getStatsForEvent(event.getId());
        return EventMapper.eventOutputDto(event, requestId, views);

    }

    @Override
    public List<EventOutputDto> getEventsByLoc(Double lat, Double lon, Double distance) {
        List<Location> locations = locationRepository.getLocations(lat, lon, distance);
        return eventRepository.searchEventsInLoc(locations).stream()
                .map(this::getEventOutputDtoShort)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventOutputDto> getAllEventsByUserId(long userId, Integer from, Integer size) {
        return eventRepository.getEventsByInitiatorIdOrderByIdAsc(userId, getPageRequest(from, size))
                .stream()
                .map(this::getEventOutputDtoShort)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventOutputDto publishingEvent(long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        if (event.getState() == State.PUBLISHED) {
            log.error("this event was published");
        }

        event.setState(State.PUBLISHED);
        eventRepository.save(event);
        long request = requestRepository.getCountConfirmedRequestByEventId(eventId);
        long views = statisticsClient.getStatsForEvent(event.getId());
        EventOutputDto eventOutputShortDto = EventMapper.eventOutputDto(event, request, views);
        log.info("<publishingEvent EVENT> publishing {} ", eventOutputShortDto);
        return eventOutputShortDto;
    }

    @Override
    @Transactional
    public EventOutputDto rejectionEvent(long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        if (event.getState() == State.CANCELED) {
            log.error("this event was rejection");
        }
        event.setState(State.CANCELED);
        eventRepository.save(event);
        long request = requestRepository.getCountConfirmedRequestByEventId(eventId);
        long views = statisticsClient.getStatsForEvent(event.getId());
        EventOutputDto eventOutputShortDto = EventMapper.eventOutputDto(event, request, views);
        log.info("<rejectionEvent > rejection {} ", eventOutputShortDto);
        return eventOutputShortDto;
    }

    @Override
    @Transactional
    public EventOutputDto updateEventByUser(long userId, EventUpdateDto eventUpdateDto) {
        Event oldEvent = getEvent(eventUpdateDto.getEventId());

        if (!Objects.equals(oldEvent.getInitiator().getId(), userId)) {
            log.error("event cannot update {} ", eventUpdateDto.getEventId());
        }
        if (!(oldEvent.getState().equals(State.PENDING) || oldEvent.getState().equals(State.CANCELED))) {
            log.error("event cannot update {} ", eventUpdateDto.getEventId());
        }
        EventInputDto eventInputDto = EventMapper.toEventInputDtoFromUpdate(eventUpdateDto, oldEvent);
        long categoryId = eventUpdateDto.getCategory();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(ObjectNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(ObjectNotFoundException::new);
        Location location = getOrCreateLocation(eventInputDto);
        Event event = eventRepository.save(EventMapper.toEvent(eventInputDto, category, user, location));
        long views = statisticsClient.getStatsForEvent(event.getId());
        EventOutputDto eventOutputDto = EventMapper.eventOutputDto(event, 0L, views);
        log.info("update event {} ", eventOutputDto);
        return eventOutputDto;

    }

    @Override
    public EventOutputDto getEventByInitiator(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        if (!event.getInitiator().getId().equals(userId)) {
            log.error("this user id not found");
        }
        long request = requestRepository.getCountConfirmedRequestByEventId(eventId);
        long views = statisticsClient.getStatsForEvent(event.getId());
        return EventMapper.eventOutputDto(event, request, views);
    }

    private Event getEvent(long id) {
        log.info("get event id {} ", id);
        return eventRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public List<EventOutputDto> getAllEvents(List<Long> users,
                                             List<State> states,
                                             List<Long> categories,
                                             String rangeStart,
                                             String rangeEnd,
                                             Integer from,
                                             Integer size) {
        List<Event> events = eventRepository.searchEvents(users,
                        states,
                        categories,
                        rangeStart,
                        rangeEnd,
                        getPageRequest(from, size))
                .stream()
                .collect(Collectors.toList());
        List<EventOutputDto> outputEvents = events
                .stream()
                .map(this::getFullOutputDto)
                .collect(Collectors.toList());
        log.info("get all events {} ", outputEvents);
        return outputEvents;

    }

    @Override
    public List<EventOutputDto> searchEventsByUser(String text,
                                                   List<Long> categories,
                                                   Boolean paid,
                                                   String rangeStart,
                                                   String rangeEnd,
                                                   Boolean onlyAvailable,
                                                   String sort,
                                                   Integer from,
                                                   Integer size) {
        List<EventOutputDto> events = eventRepository.getEvents(text,
                        categories,
                        paid,
                        rangeStart,
                        rangeEnd,
                        getPageRequest(from, size))
                .stream()
                .map(this::getEventOutputDtoShort)
                .collect(Collectors.toList());
        if (onlyAvailable != null && onlyAvailable) {
            events = events.stream()
                    .filter(e -> e.getParticipantLimit() == 0)
                    .filter(e -> e.getParticipantLimit() > requestRepository.getCountConfirmedRequestByEventId(e.getId()))
                    .collect(Collectors.toList());
        }
        return events;
    }

    @Override
    @Transactional
    public EventOutputDto editingEventByAdmin(long eventId, EventInputDto eventInputDto) {
        Event oldEvent = eventRepository.findById(eventId).orElseThrow(ObjectNotFoundException::new);
        Long request = requestRepository.getCountConfirmedRequestByEventId(eventId);
        Event event = Event.builder()
                .annotation(eventInputDto.getAnnotation() != null ? eventInputDto.getAnnotation() : oldEvent.getAnnotation())
                .category(eventInputDto.getCategory() != null ? getCategory(eventInputDto.getCategory()) : oldEvent.getCategory())
                .description(eventInputDto.getDescription() != null ? eventInputDto.getDescription() : oldEvent.getDescription())
                .eventDate(eventInputDto.getEventDate() != null ? eventInputDto.getEventDate() : oldEvent.getEventDate())
                .state(oldEvent.getState())
                .requestModeration(eventInputDto.getRequestModeration() != null ? eventInputDto.getRequestModeration() : oldEvent.getRequestModeration())
                .id(oldEvent.getId())
                .createdOn(oldEvent.getCreatedOn())
                .title(eventInputDto.getTitle() != null ? eventInputDto.getTitle() : oldEvent.getTitle())
                .paid(eventInputDto.getPaid() != null ? eventInputDto.getPaid() : oldEvent.getPaid())
                .location(eventInputDto.getLocation() != null ? eventInputDto.getLocation() : oldEvent.getLocation())
                .initiator(oldEvent.getInitiator())
                .participantLimit(eventInputDto.getParticipantLimit() != null ? eventInputDto.getParticipantLimit() : oldEvent.getParticipantLimit())
                .publishedOn(oldEvent.getPublishedOn())
                .build();

        eventRepository.save(event);
        long views = statisticsClient.getStatsForEvent(event.getId());
        EventOutputDto eventOutputShortDto = EventMapper.eventOutputDto(event, request, views);
        log.info("editing event by admin {} ", eventOutputShortDto);
        return eventOutputShortDto;


    }

    @Override
    public EventOutputDto rejectEventByInitiator(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
        event.setState(State.CANCELED);
        eventRepository.save(event);
        long request = requestRepository.getCountConfirmedRequestByEventId(eventId);
        long views = statisticsClient.getStatsForEvent(event.getId());
        EventOutputDto eventOutputDto = EventMapper.eventOutputDto(event, request, views);
        log.info("reject event {} ", eventOutputDto);
        return eventOutputDto;
    }

    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    private PageRequest getPageRequest(Integer from, Integer size) {
        int page = from < size ? 0 : from / size;
        return PageRequest.of(page, size);
    }

    private EventOutputDto getEventOutputDtoShort(Event event) {
        long requests = requestRepository.getCountConfirmedRequestByEventId(event.getId());
        long views = statisticsClient.getStatsForEvent(event.getId());
        return EventMapper.eventOutputDto(event, requests, views);
    }

    private EventOutputDto getFullOutputDto(Event event) {
        long requests = requestRepository.getCountConfirmedRequestByEventId(event.getId());
        long views = statisticsClient.getStatsForEvent(event.getId());
        return EventMapper.eventOutputDto(event, requests, views);
    }

    private Location getOrCreateLocation(EventInputDto eventInputDto) {
        Optional<Location> locationFromInput = locationRepository.findLocation(
                eventInputDto.getLocation().getLat(),
                eventInputDto.getLocation().getLon()
        );
        Location resultLocation;
        if (locationFromInput.isPresent()) {
            resultLocation = locationFromInput.get();
        } else {
            resultLocation = locationRepository.save(eventInputDto.getLocation());
            log.info("<<getOrCreateLocation> no api EVENT> create new location {}, {}, {}",
                    resultLocation.getId(), resultLocation.getLat(), resultLocation.getLon());
        }
        return resultLocation;
    }


}
