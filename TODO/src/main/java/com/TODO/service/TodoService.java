package com.TODO.service;

import com.TODO.DTO.TodoDto;
import com.TODO.entity.Todo;
import com.TODO.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public TodoDto addToDo(TodoDto todo) {
        Todo res = new Todo();
        res.setDueDate(todo.getDueDate());
        res.setDescription(todo.getDescription());
        res.setCreatedAt(LocalDateTime.now());
        res.setCompleted(todo.isCompleted());
        repo.save(res);
        TodoDto resDto = new TodoDto();
        resDto.setCreatedAt(res.getCreatedAt());
        resDto.setCompleted(res.isCompleted());
        resDto.setDueDate(res.getDueDate());
        resDto.setDescription(res.getDescription());
        resDto.setId(res.getId());
        return  resDto;
    }

    public Optional<TodoDto> getTodo(int id) {
        Todo todo = repo.findById(id).orElseThrow(null);
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setCompleted(todo.isCompleted());
        dto.setDescription(todo.getDescription());
        dto.setDueDate(todo.getDueDate());
        dto.setCreatedAt(todo.getCreatedAt());
        return Optional.of(dto);
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

    public String deleteTodo(int id) {
        repo.deleteById(id);
        return "Todo of "+id+" has been deleted";
    }

    public TodoDto convertToDto(Todo todo){
        return modelMapper.map(todo, TodoDto.class);
    }

    public Todo convertToEntity(TodoDto todoDto){
        return modelMapper.map(todoDto , Todo.class);
    }
}