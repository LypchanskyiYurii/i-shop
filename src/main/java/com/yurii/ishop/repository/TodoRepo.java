package com.yurii.ishop.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TodoRepo extends CrudRepository<TodoEntity, Long> {

    Collection<TodoEntity> findAll();

}
