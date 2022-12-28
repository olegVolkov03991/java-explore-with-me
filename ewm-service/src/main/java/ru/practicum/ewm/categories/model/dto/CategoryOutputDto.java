package ru.practicum.ewm.categories.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryOutputDto {
    private Long id;
    private String name;
}
