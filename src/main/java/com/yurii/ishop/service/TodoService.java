package com.yurii.ishop.service;

import com.yurii.ishop.entity.TodoEntity;
import com.yurii.ishop.entity.UserEntity;
import com.yurii.ishop.model.Todo;
import com.yurii.ishop.repository.TodoRepo;
import com.yurii.ishop.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private TodoRepo todoRepo;
    private UserRepo userRepo;

    public List<Todo> getAllTodos() {
        return todoRepo.findAll().stream()
                .map(Todo::toModel)
                .collect(Collectors.toList());
    }

    public Todo createTodo(TodoEntity todo, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo complete(Long id) {
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }

}
