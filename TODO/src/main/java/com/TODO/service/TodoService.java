package com.TODO.service;

import com.TODO.entity.Todo;
import com.TODO.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Optional<Todo> getTodo(int id) {
        return repo.findById(id);
    }

    public List<Todo> getAllTodos() {
        return repo.findAll();
    }

    public Todo updateTodo(int id,Todo todo) {
        Todo t = repo.findById(id).orElseThrow(null);
        t.setCompleted(todo.isCompleted());
        return repo.save(t);
    }

    public Todo completeUpdateTodo(int id, Todo todo) {
        Todo t = repo.findById(id).orElseThrow(null);
        t.setCompleted(todo.isCompleted());
        t.setDescription(todo.getDescription());
        t.setCreatedAt(LocalDateTime.now());
        t.setDueDate(todo.getDueDate());
        return repo.save(t);
    }
}