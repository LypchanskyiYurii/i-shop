package com.yurii.ishop.service;

import com.yurii.ishop.dto.category.CategoryResponseDto;
import com.yurii.ishop.entity.Category;
import com.yurii.ishop.exception.CategoryNotFoundException;
import com.yurii.ishop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final String CATEGORY_NOT_FOUND_NAME_MSG = "Category with name = %s not found";

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

}
