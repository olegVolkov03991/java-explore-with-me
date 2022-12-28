package ru.practicum.ewm.compilations.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.compilations.model.Compilation;
import ru.practicum.ewm.compilations.model.dto.CompilationInputDto;
import ru.practicum.ewm.compilations.model.dto.CompilationMapper;
import ru.practicum.ewm.compilations.model.dto.CompilationOutputDto;
import ru.practicum.ewm.compilations.repository.CompilationRepository;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.repository.EventRepository;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationsRepository;
    private final EventRepository eventRepository;


    @Override
    public CompilationOutputDto create(CompilationInputDto compilationInputDto) {
        Set<Event> events = compilationInputDto.getEvents()
                .stream()
                .map(this::getEvent)
                .collect(Collectors.toSet());
        Compilation compilations = CompilationMapper.toCompilations(compilationInputDto, events);
        compilationsRepository.save(compilations);
        CompilationOutputDto compilationOutputDto = CompilationMapper.toCompilationsOutputDto(compilations, events);
        log.info("create {} ", compilationOutputDto);
        return compilationOutputDto;

    }

    @Override
    public List<CompilationOutputDto> getCompilations(boolean pinned,
                                                      int from,
                                                      int size) {
        return compilationsRepository.findCompilationByPinnedOrderByIdAsc(pinned,
                        getPageRequest(from, size))
                .stream()
                .map(this::getCompilationsOutputDto)
                .collect(Collectors.toList());


    }

    @Override
    public CompilationOutputDto getCompilationById(long id) {

        return CompilationMapper.compilationsOutputDtoShort(
                compilationsRepository.findById(id)
                        .orElseThrow(ObjectNotFoundException::new));
    }

    @Override
    public void addEventToCompilation(long compId, long eventId) {
        compilationsRepository.addEventToCompilation(compId, eventId);
    }

    @Override
    public void deleteEventInCompilation(long compId, long eventId) {
        compilationsRepository.deleteEventToCompilation(compId, eventId);

    }

    @Override
    public void deleteCompilation(long compId) {
        compilationsRepository.deleteById(compId);
    }

    @Override
    public void pinCompilation(long compId) {
        Compilation compilation = compilationsRepository.findById(compId)
                .orElseThrow(ObjectNotFoundException::new);
        compilation.setPinned(true);
        compilationsRepository.save(compilation);
    }

    @Override
    public void unpinCompilation(long compId) {
        Compilation compilation = compilationsRepository.findById(compId)
                .orElseThrow(ObjectNotFoundException::new);
        compilation.setPinned(false);
        compilationsRepository.save(compilation);
    }

    private CompilationOutputDto getCompilationsOutputDto(Compilation compilations) {
        return CompilationMapper.compilationsOutputDtoShort(compilations);
    }

    private PageRequest getPageRequest(Integer from, Integer size) {
        int page = from < size ? 0 : from / size;
        return PageRequest.of(page, size);
    }

    private Event getEvent(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(ObjectNotFoundException::new);
    }
}
