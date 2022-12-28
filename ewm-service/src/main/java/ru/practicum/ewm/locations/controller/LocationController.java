package ru.practicum.ewm.locations.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.locations.model.dto.LocationInputDto;
import ru.practicum.ewm.locations.model.dto.LocationOutputDto;
import ru.practicum.ewm.locations.model.dto.LocationOutputDtoWithDistance;
import ru.practicum.ewm.locations.services.LocationServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/location")
public class LocationController {

    private final LocationServiceImpl locationService;

    /**
     * Добавление новой локации
     *
     * @param locationInputDto - данные добавляемой локации
     */
    @PostMapping()
    public LocationOutputDto addLocation(@Valid @RequestBody LocationInputDto locationInputDto) {
        return locationService.addLocation(locationInputDto);
    }

    /**
     * Получение полной информации о локации
     *
     * @param locId - id локации
     */
    @GetMapping("/{locId}")
    public LocationOutputDto getLocationById(@Valid @PathVariable Long locId) {
        return locationService.getLocationById(locId);
    }

    /**
     * Возвращает информацию обо всех локациях событий включая расстояние до места,
     * находящихся в области диаметром distance и центром lat,lon,
     * имеющих соответствующее название и описание
     *
     * @param lat         - широта точки центра области поиска
     * @param lon         - долгота точки центра области поиска
     * @param distance    - радиус области поиска
     * @param name        - наименование локации
     * @param description - описание локации
     * @param from        - количество элементов, которые нужно пропустить для формирования текущего набора
     * @param size        - количество элементов в наборе
     */
    @GetMapping
    public List<LocationOutputDtoWithDistance> searchLocations(@Valid @RequestParam Double lat,
                                                               @Valid @RequestParam Double lon,
                                                               @Valid @RequestParam Double distance,
                                                               @Valid @RequestParam(required = false) String name,
                                                               @Valid @RequestParam(required = false) String description,
                                                               @Valid @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                               @Valid @RequestParam(defaultValue = "10") @Positive Integer size) {
        return locationService.searchLocations(lat, lon, distance, name, description, from, size);
    }
}
