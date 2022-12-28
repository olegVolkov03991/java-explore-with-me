package ru.practicum.ewm.locations.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class LocationDto {
    @NonNull
    private Double lat;
    @NonNull
    private Double lon;
}
