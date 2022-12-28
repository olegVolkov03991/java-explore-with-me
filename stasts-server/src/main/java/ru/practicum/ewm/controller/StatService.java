package ru.practicum.ewm.controller;

import java.util.List;

public interface StatService {

    void addHit(EndpointHitDto endpointHitDto);
    List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique);
}
