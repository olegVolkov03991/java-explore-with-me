package ru.practicum.ewm.compilations.model.dto;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.compilations.model.Compilation;
import ru.practicum.ewm.events.model.Event;

import java.util.Set;

@Component
public class CompilationMapper {

    public static Compilation toCompilations(CompilationInputDto compilationsInputDto, Set<Event> events) {
        return Compilation.builder()
                .title(compilationsInputDto.getTitle())
                .pinned(compilationsInputDto.getPinned())

                .events(events)
                .build();
    }

    public static CompilationOutputDto compilationsOutputDtoShort(Compilation compilations) {
        return CompilationOutputDto.builder()
                .id(compilations.getId())
                .pinned(compilations.getPinned())
                .title(compilations.getTitle())
                .events(compilations.getEvents())
                .build();
    }

    public static CompilationOutputDto toCompilationsOutputDto(Compilation compilation, Set<Event> events) {
        return CompilationOutputDto.builder()
                .id(compilation.getId())
                .events(events)
                .title(compilation.getTitle())
                .pinned(compilation.getPinned())
                .build();
    }
}
