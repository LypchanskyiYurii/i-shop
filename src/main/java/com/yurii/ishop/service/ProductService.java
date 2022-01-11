package com.yurii.ishop.service;

import com.yurii.ishop.dto.product.ProductRequestDto;
import com.yurii.ishop.dto.product.ProductResponseDto;
import com.yurii.ishop.entity.Category;
import com.yurii.ishop.entity.Product;
import com.yurii.ishop.exception.CategoryNotFoundException;
import com.yurii.ishop.exception.ProductNotFoundException;
import com.yurii.ishop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_MSG = "Product with id = %s not found";

    private final ProductRepository productRepository;

    private final CategoryService categoryService;


    public ProductResponseDto getById(long id) throws CategoryNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MSG, id)));
        return toProductResponseDto(product);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_MSG, id)));
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        return toProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());

        Category category = categoryService.getCategoryByName(productRequestDto.getCategory().getName());
        product.setCategory(category);

        Product createdProduct = productRepository.save(product);
        return toProductResponseDto(createdProduct);
    }

    private ProductResponseDto toProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(categoryService.toCategoryResponseDto(product.getCategory()));
        return productResponseDto;
    }

}


