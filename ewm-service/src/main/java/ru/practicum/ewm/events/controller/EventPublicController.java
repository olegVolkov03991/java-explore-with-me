package ru.practicum.ewm.events.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.services.EventServiceImpl;
import ru.practicum.ewm.events.model.dto.EventFullDto;
import ru.practicum.ewm.events.model.dto.EventOutputShortDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/events")
public class EventPublicController {

    private final EventServiceImpl eventService;


    @GetMapping("/{id}")
    public EventFullDto getPublishedEventById(@PathVariable Long id) {
        return eventService.getPublishedEventById(id);
    }

    /**
     * Получение событий с возможностью фильтрации
     *
     * @param text          - текст для поиска в содержимом аннотации и подробном описании события
     * @param categories    - список идентификаторов категорий в которых будет вестись поиск
     * @param paid          - поиск только платных/бесплатных событий
     * @param rangeStart    - дата и время не раньше которых должно произойти событие
     * @param rangeEnd      - дата и время не позже которых должно произойти событие
     * @param onlyAvailable - только события у которых не исчерпан лимит запросов на участие
     * @param sort          - Вариант сортировки: по дате события или по количеству просмотров
     * @param from          - количество событий, которые нужно пропустить для формирования текущего набора
     * @param size          - количество событий в наборе
     */

    @GetMapping()
    public List<EventOutputShortDto> getEventsByLoc(HttpServletRequest request,
                                                    @RequestParam(required = false) String text,
                                                    @RequestParam(required = false) List<Long> categories,
                                                    @RequestParam(required = false) Boolean paid,
                                                    @RequestParam(required = false) String rangeStart,
                                                    @RequestParam(required = false) String rangeEnd,
                                                    @RequestParam(required = false) Boolean onlyAvailable,
                                                    @RequestParam(required = false) String sort,
                                                    @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                    @RequestParam(defaultValue = "10") @Positive Integer size) {
        return eventService.searchEventsByUser(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }
}
