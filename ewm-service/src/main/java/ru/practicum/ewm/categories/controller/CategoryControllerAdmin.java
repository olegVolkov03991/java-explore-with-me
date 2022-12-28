package ru.practicum.ewm.categories.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.model.dto.CategoryInputDto;
import ru.practicum.ewm.categories.model.dto.CategoryOutputDto;
import ru.practicum.ewm.categories.services.CategoryServiceImpl;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/categories")
public class CategoryControllerAdmin {

    private final CategoryServiceImpl categoryService;

    @PostMapping
    public CategoryOutputDto create(@RequestBody @Valid CategoryInputDto categoryInputDto) {
        return categoryService.create(categoryInputDto);
    }

    @PatchMapping
    public CategoryOutputDto update(@RequestBody @Valid CategoryInputDto categoryInputDto) {
        return categoryService.update(categoryInputDto);
    }

    @DeleteMapping("/{catId}")
    public void remove(@PathVariable int catId) {
        categoryService.remove(catId);
    }
}
