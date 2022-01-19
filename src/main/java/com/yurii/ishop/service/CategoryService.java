package com.yurii.ishop.service;

import com.yurii.ishop.dto.category.CategoryRequestDto;
import com.yurii.ishop.dto.category.CategoryResponseDto;
import com.yurii.ishop.entity.Category;
import com.yurii.ishop.exception.CategoryAlreadyExistsException;
import com.yurii.ishop.exception.CategoryNotFoundException;
import com.yurii.ishop.mapper.CategoryConverter;
import com.yurii.ishop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    private static final String CATEGORY_NOT_FOUND_NAME_MSG = "Category with name = %s not found";
    private static final String CATEGORY_WITH_NAME_ALREADY_EXISTS_MSG = "Category with name = %s already exists";

    Category getCategoryByName(String name) {
        Category category = categoryRepository.getByName(name);
        if (category == null) {
            throw new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND_NAME_MSG, name));
        }
        return category;
    }

    CategoryResponseDto toCategoryResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    @SneakyThrows
    @Transactional
    public CategoryResponseDto createIfNotExists(CategoryRequestDto categoryRequestDto) {
        String name = categoryRequestDto.getName();
        Category category = categoryRepository.getByName(name);

        if (category != null) {
            throw new CategoryAlreadyExistsException(String.format(CATEGORY_WITH_NAME_ALREADY_EXISTS_MSG, name));
        }

        category = new Category();
        category.setName(name);

        Category createdCategory = categoryRepository.save(category);
        return categoryConverter.toCategoryResponseDto(createdCategory);
    }

    public Page<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryConverter::toCategoryResponseDto);
    }

    public CategoryResponseDto getByName(String name) {
        Category category = getCategoryByName(name);
        return categoryConverter.toCategoryResponseDto(category);
    }

    public CategoryResponseDto getById(Long id) {
        Category category = getCategoryById(id);
        return categoryConverter.toCategoryResponseDto(category);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(String.format(CATEGORY_NOT_FOUND_NAME_MSG, id)));
    }

    @SneakyThrows
    @Transactional
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = getCategoryById(id);

        String name = categoryRequestDto.getName();
        Category categoryByName = categoryRepository.getByName(name);
        if (categoryByName != null) {
            throw new CategoryAlreadyExistsException(String.format(CATEGORY_WITH_NAME_ALREADY_EXISTS_MSG, name));
        }

        category.setName(name);
        Category updatedCategory = categoryRepository.save(category);
        return toCategoryResponseDto(updatedCategory);
    }

    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
