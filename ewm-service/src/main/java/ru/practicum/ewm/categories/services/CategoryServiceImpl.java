package ru.practicum.ewm.categories.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryOutputDto create(CategoryInputDto categoryInputDto) {
        if (categoryRepository.findByName(categoryInputDto.getName()) != null) {
            throw new ConflictException("this name already exists");
        }
        Category category = CategoryMapper.toCategory(categoryInputDto);
        categoryRepository.save(category);
        CategoryOutputDto categoryOutputDto = CategoryMapper.toCategoryOutPutDto(category);
        log.info("create {} ", categoryOutputDto);
        return categoryOutputDto;
    }

    @Override
    public CategoryOutputDto update(CategoryInputDto categoryInputDto) {
        if (categoryRepository.findByName(categoryInputDto.getName()) != null) {
            throw new ConflictException("this name already exists");
        }

        Category category = categoryRepository.findById(categoryInputDto.getId())
                .orElseThrow(ObjectNotFoundException::new);
        categoryRepository.deleteById(categoryInputDto.getId());
        category.setName(category.getName());
        categoryRepository.save(category);
        CategoryOutputDto categoryOutputDto = CategoryMapper.toCategoryOutPutDto(category);
        log.info("update category {} ", categoryOutputDto);
        return categoryOutputDto;
    }

    @Override
    public void remove(long id) {
        if (id == 0) {
            id = 1;
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryOutputDto> getAllById(List<Long> id,
                                              Integer from,
                                              Integer size) {
        Page<Category> categories;
        if (id == null || id.isEmpty()) {
            categories = categoryRepository.findAll(getPageRequest(from, size));
            log.info("get all categories {} ", categories);
        } else if (id.size() == 1) {
            Category category = getCategory(id.get(0));
            log.info("get category {} ", category);
            return List.of(CategoryMapper.toCategoryOutPutDto(category));
        } else {
            categories = categoryRepository.getCategoriesByIdInOrderByIdAsc(id, getPageRequest(from, size));
            if (categories.isEmpty()) {
                log.warn("page categories is empty");
                throw new ObjectNotFoundException();
            }
            categories.stream().forEach(category -> id.remove(category.getId()));
            if (!id.isEmpty()) {
                log.warn("error");
            }
        }

        List<CategoryOutputDto> outputCategories = categories.stream()
                .map(CategoryMapper::toCategoryOutPutDto)
                .collect(Collectors.toList());
        log.info("get all categories {} ", outputCategories);
        return outputCategories;
    }

    @Override
    public CategoryOutputDto getById(long id) {
        if (id == 0) {
            id = 1;
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        CategoryOutputDto categoryOutputDto = CategoryMapper.toCategoryOutPutDto(category);
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
