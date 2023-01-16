package ru.practicum.ewm.compilations.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.model.dto.CompilationInputDto;
import ru.practicum.ewm.compilations.model.dto.CompilationOutputDto;
import ru.practicum.ewm.compilations.services.CompilationService;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationController {

    private final CompilationService compilationsService;

    @PostMapping()
    public CompilationOutputDto create(@Valid @RequestBody CompilationInputDto compilationsInputDto) {
        return compilationsService.create(compilationsInputDto);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@Valid @PathVariable long compId) {
        compilationsService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    void addEventToCompilation(@Valid @PathVariable long compId,
                               @Valid @PathVariable long eventId) {
        compilationsService.addEventToCompilation(compId, eventId);

    }

    @DeleteMapping("/{compId}/events/{eventId}")
    void deleteEventInCompilationForEventId(@Valid @PathVariable long compId,
                                            @Valid @PathVariable long eventId) {
        compilationsService.deleteEventInCompilation(compId, eventId);
    }

    @PatchMapping(("/{compId}/pin"))
    void pinCompilation(@Valid @PathVariable long compId) {
        compilationsService.pinCompilation(compId);
    }

    @DeleteMapping(("/{compId}/pin"))
    void unpinCompilation(@Valid @PathVariable long compId) {
        compilationsService.unpinCompilation(compId);
    }


}
