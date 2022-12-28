package ru.practicum.ewm.categories.services;

import ru.practicum.ewm.categories.model.dto.CategoryInputDto;
import ru.practicum.ewm.categories.model.dto.CategoryOutputDto;

import java.util.List;

public interface CategoryService {

    List<CategoryOutputDto> getAllById(List<Long> id,
                                       Integer from,
                                       Integer size);

    CategoryOutputDto create(CategoryInputDto categoryInputDto);

    CategoryOutputDto update(CategoryInputDto categoryInputDto);

    CategoryOutputDto getById(long id);

    void remove(long id);


}
