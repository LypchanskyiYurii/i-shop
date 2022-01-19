package com.yurii.ishop.mapper;

import com.yurii.ishop.dto.product.ProductResponseDto;
import com.yurii.ishop.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductConverter {

    private final CategoryConverter categoryConverter;

    public ProductResponseDto toProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(categoryConverter.toCategoryResponseDto(product.getCategory()));
        return productResponseDto;
    }

}
