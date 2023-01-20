package ru.practicum.ewm.categories.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.categories.model.dto.CategoryInputDto;
import ru.practicum.ewm.categories.model.dto.CategoryMapper;
import ru.practicum.ewm.categories.model.dto.CategoryOutputDto;
import ru.practicum.ewm.categories.repository.CategoryRepository;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryOutputDto create(CategoryInputDto categoryInputDto) {
        if (categoryRepository.existsByName(categoryInputDto.getName())) {
            throw new ConflictException("this name already exists");
        }
        Category category = CategoryMapper.toCategory(categoryInputDto);
        categoryRepository.save(category);
        log.info("create {} ", categoryInputDto.getName());
        return CategoryMapper.toCategoryOutputDto(category);
    }

    @Override
    @Transactional
    public CategoryOutputDto update(CategoryInputDto categoryInputDto) {
        if (categoryRepository.existsByName(categoryInputDto.getName())) {
            throw new ConflictException("this name already exists");
        }

        Category category = categoryRepository.findById(categoryInputDto.getId())
                .orElseThrow(ObjectNotFoundException::new);
        category.setName(categoryInputDto.getName());
        categoryRepository.save(category);
        CategoryOutputDto categoryOutputDto = CategoryMapper.toCategoryOutputDto(category);
        log.info("update category {} ", categoryOutputDto);
        return categoryOutputDto;
    }

    @Override
    @Transactional
    public void remove(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryOutputDto> getAllById(List<Long> ids,
                                              Integer from,
                                              Integer size) {
        Page<Category> categories;
        if (ids == null || ids.isEmpty()) {
            categories = categoryRepository.findAll(getPageRequest(from, size));
            log.info("get all categories {} ", categories);
        } else if (ids.size() == 1) {
            Category category = getCategory(ids.get(0));
            log.info("get category {} ", category);
            return List.of(CategoryMapper.toCategoryOutputDto(category));
        } else {
            categories = categoryRepository.getCategoriesByIdIn(ids, getPageRequest(from, size));
            if (categories.isEmpty()) {
                log.warn("page categories is empty");
                throw new ObjectNotFoundException();
            }
        }

        List<CategoryOutputDto> outputCategories = categories.stream()
                .map(CategoryMapper::toCategoryOutputDto)
                .collect(Collectors.toList());
        log.info("get all categories {} ", outputCategories);
        return outputCategories;
    }

    @Override
    public CategoryOutputDto getById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        CategoryOutputDto categoryOutputDto = CategoryMapper.toCategoryOutputDto(category);
        log.info("get category {} ", categoryOutputDto);
        return categoryOutputDto;
    }

    private PageRequest getPageRequest(Integer from, Integer size) {
        int page = from < size ? 0 : from / size;
        return PageRequest.of(page, size);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(ObjectNotFoundException::new);
    }
}
