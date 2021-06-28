package com.yurii.ishop.repository;

import com.yurii.ishop.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TodoRepo extends CrudRepository<TodoEntity, Long> {

    Collection<TodoEntity> findAll();

}
