package ru.practicum.ewm.categories.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.model.dto.CategoryOutputDto;
import ru.practicum.ewm.categories.services.CategoryServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryControllerPublic {

    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryOutputDto> getAllById(@Valid @PathVariable(required = false) List<Long> ids,
                                              @Valid  @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                              @Valid  @RequestParam(defaultValue = "10") @Positive Integer size) {
        return categoryService.getAllById(ids, from, size);
    }

    @GetMapping("/{id}")
    public CategoryOutputDto getById(@Valid @PathVariable long id) {
        return categoryService.getById(id);
    }
}
