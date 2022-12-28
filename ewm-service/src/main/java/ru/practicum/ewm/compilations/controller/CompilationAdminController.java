package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.model.dto.CompilationOutputDto;
import ru.practicum.ewm.compilations.services.CompilationServiceImpl;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/compilations")
public class CompilationAdminController {

    private final CompilationServiceImpl compilationsService;

    @GetMapping()
    public List<CompilationOutputDto> getCompilations(@RequestParam(required = false) boolean pinned,
                                                      @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                      @RequestParam(defaultValue = "10") @Positive Integer size) {
        return compilationsService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationOutputDto getCompilationsById(@PathVariable Long compId) {
        return compilationsService.getCompilationById(compId);
    }

}
