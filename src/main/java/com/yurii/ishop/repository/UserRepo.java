package com.yurii.ishop.repository;

import com.yurii.ishop.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername (String username);

}
