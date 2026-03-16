package com.TODO.service;

import com.TODO.entity.Todo;
import com.TODO.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TodoService {
    private TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public Todo addToDo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        return repo.save(todo);
    }
}