package ru.practicum.ewm.locations.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.exceptions.BadReqestException;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.locations.model.dto.LocationInputDto;
import ru.practicum.ewm.locations.model.dto.LocationMapper;
import ru.practicum.ewm.locations.model.dto.LocationOutputDto;
import ru.practicum.ewm.locations.model.dto.LocationOutputDtoWithDistance;
import ru.practicum.ewm.locations.repository.LocationRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public LocationOutputDto addLocation(LocationInputDto locationInputDto) {
        if (locationRepository.findLocation(locationInputDto.getLat(), locationInputDto.getLon()).isPresent()) {
            throw new BadReqestException();
        }
        Location location = LocationMapper.toLocationFromInput(locationInputDto);
        return LocationMapper.toLocationOutput(locationRepository.save(location));
    }

    @Override
    public LocationOutputDto getLocationById(Long locId) {
        Location location = locationRepository.findById(locId)
                .orElseThrow(ObjectNotFoundException::new);
        return LocationMapper.toLocationOutput(location);
    }

    @Override
    public List<LocationOutputDtoWithDistance> searchLocations(Double lat,
                                                               Double lon,
                                                               Double distance,
                                                               String name,
                                                               String description,
                                                               Integer from,
                                                               Integer size) {
        return locationRepository.searchLocations(lat, lon, distance, name, description, getPageRequest(from, size)).stream()
                .collect(Collectors.toList());
    }


    private PageRequest getPageRequest(Integer from, Integer size) {
        int page = from < size ? 0 : from / size;
        return PageRequest.of(page, size);
    }
}
