package com.yurii.ishop.dto.product;

import com.yurii.ishop.dto.category.CategoryResponseDto;
import com.yurii.ishop.entity.Category;
import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private double price;
    private CategoryResponseDto category;
}
