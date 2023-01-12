package ru.practicum.ewm.compilations.model.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
public class CompilationInputDto {

    private Long id;

    @NotNull
    private String title;

    private Boolean pinned;

    private Set<Long> events;
}
