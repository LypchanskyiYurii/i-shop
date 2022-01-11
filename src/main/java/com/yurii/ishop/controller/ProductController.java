package com.yurii.ishop.controller;

import com.yurii.ishop.dto.product.ProductRequestDto;
import com.yurii.ishop.dto.product.ProductResponseDto;
import com.yurii.ishop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.create(productRequestDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable("productId") Long productId, @RequestBody @Valid ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.update(productId, productRequestDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> delete(@PathVariable("productId") Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
