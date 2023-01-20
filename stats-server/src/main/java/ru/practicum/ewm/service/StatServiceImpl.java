package ru.practicum.ewm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.model.EndpointHit;
import ru.practicum.ewm.model.EndpointHitDto;
import ru.practicum.ewm.model.StatMapper;
import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.repository.StatRepository;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    @Override
    @Transactional
    public void addHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = StatMapper.toEndpointHit(endpointHitDto);
        EndpointHit result = statRepository.save(endpointHit);
        log.info("Add hit at uri={} from ip={} and app={}.",
                result.getUri(), result.getAttributes().getIp(), result.getAttributes().getApp());
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
