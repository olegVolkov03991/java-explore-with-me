package ru.practicum.ewm.service;

import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.model.EndpointHitDto;

import java.util.List;

public interface StatService {

    void addHit(EndpointHitDto endpointHitDto);
    List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique);
}
