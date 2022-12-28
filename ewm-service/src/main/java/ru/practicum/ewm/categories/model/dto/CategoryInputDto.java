package ru.practicum.ewm.categories.model.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CategoryInputDto {
    private Long id;
    @NotNull
    private String name;
}
