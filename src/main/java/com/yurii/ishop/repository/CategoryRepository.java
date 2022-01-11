package com.yurii.ishop.repository;

import com.yurii.ishop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getByName(String name);

}
