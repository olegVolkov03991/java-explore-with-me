package ru.practicum.ewm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.model.EndpointHitDto;
import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.service.StatServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "")
public class StatsController {

    private final StatServiceImpl statService;

    @PostMapping("/hit")
    public void addHit(@RequestBody @Valid EndpointHitDto endpointHitDto) {
        statService.addHit(endpointHitDto);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(@RequestParam() String start,
                                    @RequestParam() String end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        return statService.getStats(start, end, uris, unique);
    }

}
