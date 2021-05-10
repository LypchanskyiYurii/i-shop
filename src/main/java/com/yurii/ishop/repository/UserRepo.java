package com.yurii.ishop.repository;

import com.yurii.ishop.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
