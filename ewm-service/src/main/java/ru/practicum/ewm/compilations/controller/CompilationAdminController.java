package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.model.dto.CompilationOutputDto;
import ru.practicum.ewm.compilations.services.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/compilations")
public class CompilationAdminController {

    private final CompilationService compilationsService;

    @GetMapping()
    public List<CompilationOutputDto> getCompilations(@Valid @RequestParam(required = false) boolean pinned,
                                                      @Valid @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                      @Valid @RequestParam(defaultValue = "10") @Positive Integer size) {
        return compilationsService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationOutputDto getCompilationsById(@Valid @PathVariable Long compId) {
        return compilationsService.getCompilationById(compId);
    }

}
