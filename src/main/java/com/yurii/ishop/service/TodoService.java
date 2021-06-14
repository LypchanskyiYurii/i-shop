package com.yurii.ishop.service;

import com.yurii.ishop.entity.TodoEntity;
import com.yurii.ishop.entity.UserEntity;
import com.yurii.ishop.repository.TodoRepo;
import com.yurii.ishop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public TodoEntity createTodo(TodoEntity todo, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return todoRepo.save(todo);
    }

    public TodoEntity complete(Long id) {
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return todoRepo.save(todo);
    }
}
