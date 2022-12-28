package ru.practicum.ewm.categories.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.model.dto.CategoryOutputDto;
import ru.practicum.ewm.categories.services.CategoryServiceImpl;


import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/categories")
public class CategoryControllerPublic {

    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryOutputDto> getAllById(@PathVariable(required = false) List<Long> categoryId,
                                              @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                              @RequestParam(defaultValue = "10") @Positive Integer size){
        return categoryService.getAllById(categoryId, from, size);
    }

    @GetMapping("/{id}")
    public CategoryOutputDto getById(@PathVariable long id){
        return categoryService.getById(id);
    }
}
