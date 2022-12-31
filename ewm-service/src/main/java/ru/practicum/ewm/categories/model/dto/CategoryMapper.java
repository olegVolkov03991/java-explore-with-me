package ru.practicum.ewm.categories.model.dto;

import ru.practicum.ewm.categories.model.Category;

public class CategoryMapper {
    public static Category toCategory(CategoryInputDto categoryInputDto){
        return Category.builder()
                .name(categoryInputDto.getName())
                .build();
    }

    public static CategoryOutputDto toCategoryOutputDto(Category category){
       return CategoryOutputDto.builder()
               .id(category.getId())
               .name(category.getName())
               .build();
    }

}
