package ru.practicum.ewm.compilations.services;

import ru.practicum.ewm.compilations.model.dto.CompilationInputDto;
import ru.practicum.ewm.compilations.model.dto.CompilationOutputDto;

import java.util.List;

public interface CompilationService {
    CompilationOutputDto create(CompilationInputDto compilationsInputDto);

    List<CompilationOutputDto> getCompilations(boolean pinned,
                                               int from,
                                               int size);

    CompilationOutputDto getCompilationById(long id);

    void deleteEventInCompilation(long compId,
                                  long eventId);

    void addEventToCompilation(long compId,
                               long eventId);

    void deleteCompilation(long compId);

    void unpinCompilation(long compId);

    void pinCompilation(long compId);

}
