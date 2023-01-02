package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.repository.StatRepository;
import ru.practicum.ewm.model.EndpointHit;
import ru.practicum.ewm.model.EndpointHitDto;
import ru.practicum.ewm.model.StatMapper;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    @Override
    public void addHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = StatMapper.toEndpointHit(endpointHitDto);

        statRepository.saveAndFlush(endpointHit);


    }

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique) {
        if (unique) {
            return statRepository.getViewWithUniqueIP(start, end, (uris == null ? Collections.emptyList() : uris));
        } else {
            return statRepository.getViewWithDuplicateIP(start, end, (uris == null ? Collections.emptyList() : uris));
        }
    }
}
