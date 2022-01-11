package com.yurii.ishop.dto.product;

import com.yurii.ishop.dto.category.CategoryRequestDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequestDto {

    @NotBlank(message = "{name.not.empty}")
    private String name;

    @Min(value = 0, message = "{price.size}")
    private double price;

    @Valid
    @NotNull(message = "{category.not.null}")
    private CategoryRequestDto category;

}
