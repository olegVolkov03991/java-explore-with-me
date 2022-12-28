package ru.practicum.ewm.compilations.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.model.dto.CompilationInputDto;
import ru.practicum.ewm.compilations.model.dto.CompilationOutputDto;
import ru.practicum.ewm.compilations.services.CompilationServiceImpl;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/compilations")
public class CompilationController {

    private final CompilationServiceImpl compilationsService;

    @PostMapping()
    public CompilationOutputDto create(@Valid @RequestBody CompilationInputDto compilationsInputDto) {
        return compilationsService.create(compilationsInputDto);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable long compId) {
        compilationsService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    void addEventToCompilation(@PathVariable long compId,
                               @PathVariable long eventId) {
        compilationsService.addEventToCompilation(compId, eventId);

    }

    @DeleteMapping("/{compId}/events/{eventId}")
    void deleteEventInCompilationForEventId(@PathVariable long compId,
                                            @PathVariable long eventId) {
        compilationsService.deleteEventInCompilation(compId, eventId);
    }

    @PatchMapping(("/{compId}/pin"))
    void pinCompilation(@PathVariable long compId) {
        compilationsService.pinCompilation(compId);
    }

    @DeleteMapping(("/{compId}/pin"))
    void unpinCompilation(@PathVariable long compId) {
        compilationsService.unpinCompilation(compId);
    }

//    @GetMapping()
//    public List<CompilationOutputDto> getCompilations(@RequestParam(required = false) Boolean pinned,
//                                                      @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
//                                                      @RequestParam(defaultValue = "10") @Positive Integer size) {
//        return compilationsService.getCompilations(pinned, from, size);
//    }


}
